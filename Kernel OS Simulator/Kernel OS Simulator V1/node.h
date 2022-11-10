/* SYSC4001A Assignment 1
 * Cade Theriault
 * 101192252
 */

#ifndef __NODE_H__
#define __NODE_H__ 
#include <stdio.h>
#include <stdlib.h>
#include "structures.h"


node_t *new_node(pcb_t *val);

void append_node(linkedlist_t** pcb_list, node_t* new_node );

linkedlist_t* new_linkedlist();

node_t *get_and_remove_node(linkedlist_t** list);



#endif
