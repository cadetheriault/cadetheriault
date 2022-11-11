/* SYSC4001A Assignment 2
 * Cade Theriault
 * 101192252
 */

#ifndef __PCB_H__
#define __PCB_H__
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "node.h"
#include "structures.h"

int load_csv_as_pcb (char* textfile, linkedlist_t** pcb_llist, int is_priority);

void string_pcb(char* o_file, pcb_t* pcb, int sys_time, char* old_state, int is_priority);

#endif
