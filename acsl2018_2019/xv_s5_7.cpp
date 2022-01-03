#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#include <map>
#include <string>

namespace rikosee_ju_take_jor_ejm
{


typedef long long i64;

char* read_entire_file(const char* path)
{
    FILE* f = fopen(path, "rb");
    fseek(f, 0, SEEK_END);
    int size = ftell(f);
    fseek(f, 0, SEEK_SET);
    char* data = (char*) malloc(size + 2);
    fread(data, 1, size, f);
    data[size] = 0;
    data[size + 1] = 0;
    fclose(f);
    return data;
}

char* next_program(char** ptr)
{
    char* str = *ptr;
    while (*str && isspace(*str)) str++;
    if (!*str) return NULL;
    char* start = str;
    while (*str &&
           !((str[0] == '\n' && str[1] == '\n') ||
             (str[0] == '\r' && str[1] == '\r') ||
             (str[0] == '\r' && str[1] == '\n' && str[2] == '\r' && str[3] == '\n') ||
             (str[0] == '\n' && str[1] == '\r' && str[2] == '\n' && str[3] == '\r')))
        str++;
    str[0] = 0;
    str[1] = 0;
    *ptr = str + 2;
    return start;
}

char* next_line(char** ptr)
{
    char* str = *ptr;
    while (*str && isspace(*str)) str++;
    if (!*str) return NULL;
    char* start = str;
    while (*str && (*str != '\n' && *str != '\r')) str++;
    *str = 0;
    *ptr = str + 1;
    return start;
}

char* next_token(char** ptr)
{
    char* str = *ptr;
    while (*str && isspace(*str)) str++;
    if (!*str) return NULL;
    char* start = str;
    while (*str && !isspace(*str)) str++;
    *str = 0;
    *ptr = str + 1;
    return start;
}

char to_lowercase(char c)
{
    if (c >= 'A' && c <= 'Z')
        return c - 'A' + 'a';
    return c;
}

void convert_to_uppercase(char* op)
{
    for (char* s = op; *s; s++)
        if (*s >= 'a' && *s <= 'z')
            *s = *s - 'a' + 'A';
}

bool match(const char* a, const char* b)
{
    size_t len = strlen(a);
    if (len != strlen(b)) return false;
    for (size_t i = 0; i < len; i++)
        if (to_lowercase(a[i]) != to_lowercase(b[i]))
            return false;
    return true;
}

enum Opcode_Check_Result
{
    NOT_OPCODE,
    OPCODE_NO_LOC,
    OPCODE_WITH_LOC,
};

Opcode_Check_Result check_opcode(char* token)
{
    if (match(token, "LOAD" )) return OPCODE_WITH_LOC;
    if (match(token, "STORE")) return OPCODE_WITH_LOC;
    if (match(token, "ADD"  )) return OPCODE_WITH_LOC;
    if (match(token, "SUB"  )) return OPCODE_WITH_LOC;
    if (match(token, "MULT" )) return OPCODE_WITH_LOC;
    if (match(token, "DIV"  )) return OPCODE_WITH_LOC;
    if (match(token, "BG"   )) return OPCODE_WITH_LOC;
    if (match(token, "BE"   )) return OPCODE_WITH_LOC;
    if (match(token, "BL"   )) return OPCODE_WITH_LOC;
    if (match(token, "BU"   )) return OPCODE_WITH_LOC;
    if (match(token, "READ" )) return OPCODE_WITH_LOC;
    if (match(token, "PRINT")) return OPCODE_WITH_LOC;
    if (match(token, "DC"   )) return OPCODE_WITH_LOC;
    if (match(token, "END"  )) return OPCODE_NO_LOC;
    return NOT_OPCODE;
}

struct Instruction
{
    Instruction* next;
    char* label;
    char* op;
    char* loc;
};

std::map<std::string, i64>          value_map;
std::map<std::string, Instruction*> label_map;

i64 get_const(char* token)
{
    if (*token == '=') token++;
    i64 value;
    sscanf(token, "%lld", &value);
    return value;
}

i64 get_value(char* token)
{
    if (*token == '=' || (*token >= '0' && *token <= '9'))
        return get_const(token);
    return value_map[token];
}

i64 my_mod(i64 a)
{
    bool neg = false;
    if (a < 0) { neg = true; a = -a; }
    a %= 1000000;
    if (neg) a = -a;
    return a;
}

Instruction* execute(Instruction* instr, char** inputs, i64* acc)
{
    char* label = instr->label;
    char* op    = instr->op;
    char* loc   = instr->loc;

    // printf("%10s %10s %10s    ACC = %lld\n", label, op, loc, *acc);

         if (match(op, "DC"))    value_map[label] = get_const(loc);
    else if (match(op, "LOAD"))  *acc = get_value(loc);
    else if (match(op, "STORE")) value_map[loc] = *acc;
    else if (match(op, "ADD"))   *acc += get_value(loc);
    else if (match(op, "SUB"))   *acc -= get_value(loc);
    else if (match(op, "MULT"))  *acc *= get_value(loc);
    else if (match(op, "DIV"))   *acc /= get_value(loc);
    else if (match(op, "READ"))  value_map[loc] = my_mod(get_const(next_token(inputs)));
    else if (match(op, "PRINT")) {}
    else if (match(op, "BG"))    { if (*acc >  0) return label_map[loc]; }
    else if (match(op, "BL"))    { if (*acc <  0) return label_map[loc]; }
    else if (match(op, "BE"))    { if (*acc == 0) return label_map[loc]; }
    else if (match(op, "BU"))    return label_map[loc];
    else if (match(op, "END"))   return NULL;
    return instr->next;
}

void entry()
{
    char* data = read_entire_file("as7-test.txt");
    char* program;
    char* token;

    while ((program = next_program(&data)) != NULL)
    {
        i64 acc = 0;
        char* inputs = next_line(&program);

        Instruction* head = NULL;
        Instruction* prev = NULL;

        while ((token = next_token(&program)) != NULL)
        {
            char* label = NULL;
            Opcode_Check_Result result = check_opcode(token);
            if (result == NOT_OPCODE)
            {
                label = token;
                token = next_token(&program);
                result = check_opcode(token);
            }

            char* op = token;
            convert_to_uppercase(op);

            char* loc = NULL;
            if (result == OPCODE_WITH_LOC)
                loc = next_token(&program);

            Instruction* instr = new Instruction;
            instr->label = label;
            instr->op    = op;
            instr->loc   = loc;
            instr->next  = NULL;

            if (label)
                label_map[label] = instr;

            if (prev)
                prev->next = instr;
            else
                head = instr;
            prev = instr;
        }

        while (head)
        {
            head = execute(head, &inputs, &acc);
            acc = my_mod(acc);
        }
        printf("%lld\n", acc);
    }
}


}

int main(int argc, char** argv)
{
    rikosee_ju_take_jor_ejm::entry();
    return EXIT_SUCCESS;
}
