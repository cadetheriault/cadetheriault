SQLite format 3   @    �                                                             � .�   � zA�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  C_indexFilenameBrowseCREATE INDEX Filename ON Browse(Filename)4KindexTagBrowseCREATE INDEX Tag ON Browse(Tag)7OindexNameBrowseCREATE INDEX Name ON Browse(Name)��atableBrowseBrowseCREATE TABLE Browse (Kind INTEGER,Name TEXT,Tag TEXT,Filename TEXT,Lineno INTEGER,Text TEXT,Extra INTEGER)   �    ����rL����mL���fE
�
�
�
y
\
$	�	�	�	�	g	J	���}W9����uR,��s=��kO���k8��rN5	�|O���K��D���� � �; � �9mallocpcb.c�8#pcbpcb.cpcb_t *pcb �7fgetspcb.c0�.%5removed_nodenode.c0node_t* removed_nodeS�-	3oget_and_remove_nodenode.c*node_t *get_and_remove_node(linkedlist_t** list){'�,3listnode.c*linkedlist_t** list�+mallocnode.c$)�*5llistnode.c$linkedlist_t* llist <�)	)Knew_linkedlistnode.c"linkedlist_t* new_linkedlist(){*�(/curr_nodenode.cnode_t* curr_nodeX�'		#�append_nodenode.cvoid append_node(linkedlist_t** pcb_list, node_t* new_node ){/�&;pcb_listnode.clinkedlist_t** pcb_list)�%/new_nodenode.cnode_t* new_node �$mallocnode.c
!�#'nodenode.c
node_t* node 5�"	Inew_nodenode.c	node_t *new_node(pcb_t *val) {�!!valnode.c	pcb_t *valH� %!%Elinkedlist_tLinkedListstructures.h$struct LinkedList { node_t * �!%LinkedListstructures.h"0�!%%headLinkedListstructures.h#node_t *head5�%7node_tNodestructures.hstruct Node { pcb_t *�%Nodestructures.h)�%#dataNodestructures.hpcb_t *data/�%/nextNodestructures.hstruct Node *next.�%-pcb_tPCBstructures.hstruct PCB { int�%PCBstructures.h#�%pidPCBstructures.hint pid5�%%-arrival_timePCBstructures.hint arrival_time9�)%1total_cpu_timePCBstructures.hint total_cpu_time5�%%-io_frequencyPCBstructures.hint io_frequency3�#%+io_durationPCBstructures.hint io_duration/�%'exec_timePCBstructures.hint exec_time/�%'wait_timePCBstructures.hint wait_time)�%#statePCBstructures.hchar* state&�-%
TERMINATED_STATEstructures.h#�'%
WAITING_STATEstructures.h �!%
EXEC_STATEstructures.h!�#%
READY_STATEstructures.h�%
NEW_STATEstructures.h&�
-%
__STRUCTURES_H__structures.h�	
__PCB_H__pcb.h�!
__NODE_H__node.h�!string_pcbmain.ci�#append_nodemain.cf#�3get_and_remove_nodemain.ce3�'9new_exec_nodemain.cenode_t* new_exec_node �!string_pcbmain.c^�#append_nodemain.cZ#�3get_and_remove_nodemain.cY3� '9new_wait_nodemain.cYnode_t* new_wait_node �!string_pcbmain.cP#�~3get_and_remove_nodemain.cN3�}'9new_term_nodemain.cNnode_t* new_term_node �|!string_pcbmain.cE�{#append_nodemain.cC#�z3get_and_remove_nodemain.cB5�y);new_ready_nodemain.cBnode_t* new_ready_node �x!string_pcbmain.c:�w#append_nodemain.c8#�v3get_and_remove_nodemain.c75�u);new_ready_nodemain.c7node_t* new_ready_node �t+load_csv_as_pcbmain.c.+�s#-load_resultmain.c.int load_result �r)new_linkedlistmain.c*3�q!?wait_llistmain.c*linkedlist_t *wait_llist �p)new_linkedlistmain.c'3�o!?exec_llistmain.c'linkedlist_t *exec_llist �n)new_linkedlistmain.c$5�m#Aready_llistmain.c$linkedlist_t *ready_llist �l)new_linkedlistmain.c!0�k;pcb_llistmain.c linkedlist_t *pcb_llist�jfclosemain.c�ifprintfmain.c�hfopenmain.c�gfclosemain.c�ffopenmain.c-�e#1output_filemain.cFILE* output_file #�d'o_filemain.cchar* o_file #�c'i_filemain.cchar* i_file 1�b)3curr_exec_timemain.cint curr_exec_time +�a#-system_timemain.cint system_time )�`!+system_runmain.cint system_run 4�_	Omainmain.cint main(int argc, char** argv) {�^argcmain.cint argc�]#argvmain.cchar** argv   
	!��6fgetspcb.c�5fopenpcb.c�4#filepcb.cFILE *file !�3)textpcb.cchar text[300]&�21delimpcb.cconst char* delim ^�1		+�load_csv_as_pcbpcb.c
int load_csv_as_pcb (char* textfile, linkedlist_t** pcb_llist) {%�0)textfilepcb.c
char* textfile0�/=pcb_llistpcb.c
linkedlist_t** pcb   �9
	E t:	�-	�R{	�$	o��	��K�������
@
*
�"{
��\	Kc
�
�

j
_	
u	��04�\|P��<
�
��1�	���	%
J=��O+����u�f�eF
�u�
��
V�	VR	+p	<�o	d�;� �
4
�F�o�

�	A�?	�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               fclose3P	list3,malloc3+
llist3*	head3node_t3	Node3	data3	next3
pcb_t3PCB3!string_pcb33get_and_remove_node3	text33	node3#new_node3"val3!	main2�	argc2�	argv2�	(	new_node3%malloc3$	file34textfile30pcb_llist3/
	pid3	� pc
delim32pcb_list3&
state3'WAITING_STATE3!EXEC_STATE3#READY_STATE3NEW_STATE3-__STRUCTURES_H__3
	� 
fopen35wait_time3
& 
& load+load_csv_as_pcb31#system_time2�!system_run2�
� 	atoi3<strtok3;	atoi3:malloc39pcb38
fgets37
fgets36
fopen2�#output_file2�o_file2�i_file2�)curr_exec_time2�

� strtok3=exec_time3@ 	atoi3>fclose2�6 #io_duration3fclose2�
fopen2�	atoi3@H fprintf2�Y a mastrtok3?pcb_llist2�� 	node3Dstrtok3C	atoi3Bstrtok3Acurr_node3(pcb3J__PCB_H__3	)new_linkedlist2�#ready_llist2�)new_linkedlist2�m� 	llist#append_node3'mfclose3Gnew_node3E)new_linkedlist2�!wait_llist2�)new_linkedlist2�!exec_llist2�o_file3K�#load_result2��sys_time3Iold_state3H#append_node3F)new_ready_node2�+load_csv_as_pcb2�� #output_file3M!string_pcb3L%io_frequency3-TERMINATED_STATE3!string_pcb2�#append_node2�3get_and_remove_node2�)new_ready_node2�!string_pcb2�#append_node2�3get_and_remove_node2� � 
append)new_linkedlist3)  pcb2�= 
fopen3N'new_term_node2�V "sys_time2�r !old_state2�r afprintf3O)total_cpu_time3� &string_pcb2�� o_file2�% 
data2�3get_and_remove_node2�� output_file2�#append_node3!string_pcb2�% $fclose2�% fprintf2�% fopen2�%arrival_time3!string_pcb3'new_wait_node3 �k Node!LinkedList3'new_exec_node33get_and_remove_node3�� %removed_node3.%linkedlist_t3 !__NODE_H__3#append_node3   3new_linkedlist2�   8linkedlist_t2�   &LinkedLis3get_and_remove_node3-
   t�2���)#?������������".����������|vpjd^XRLF@��9g�6������
4�����0XR������������ysm,&?�����|vpj(���E��H�aXOOOO                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   � LinkedList-
� .�6 0,6 1C3.3/33
2�2�2�332�332�2�30E 32�g PCB33231� � � 33B3A3@3?3>3=3<3;3:39383736353433�J 2�� ���3PCB32�2�$PCB3PCB3PCB3PCB33D3C� 3!j 3E	Node3PCB3PCB3PCB33N3M3L3K3J3I3H3G3F2�2�2�3"	Node32�2�3#2�3$2�3'3&3%2�2�3)3(2�2�2�2�3O2�3*���3	333333333 2�2�2�2�2�2�2�2�2�2�2�3+!LinkedList3 !LinkedList3	Node33P3-3,
� t

�
�
�
x
l
`
T
H�sg[OC7+
�
�
�
�����"
�:X����|pdXL\��.Lpd�����
�
=
2
'
��"���������|��������wlaVK@5*Q
�
����?-�����zh�:(������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
 node.c.�U node.c0,U node.c1Cnode.c3.
pcb.c31
pcb.c30
pcb.c3/main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�
pcb.c32%structures.h3%structures.h3
main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�main.c2�F pcb
pcb.c33main.c2�main.c2�main.c2�main.c2�%structures.h3%structures.h3%structures.h3%structures.h3
pcb.c34%structures.h3%structures.h3
pcb.h3	node.c3!%structures.h3%structures.h3%structures.h3%structures.h3%structures.h3%structures.h3%structures.h3node.c3#node.c3"main.c2�main.c2�main.c2�main.c2�node.c3$main.c2�   nonode.c3%main.c2�node.c3'node.c3&
pcb.c3>
pcb.c3=
pcb.c3<
pcb.c3;
pcb.c3:
pcb.c39
pcb.c38
pcb.c37
pcb.c36
pcb.c35%structures.h3node.h3node.c3(
pcb.c3P
pcb.c3O
pcb.c3N
pcb.c3M
pcb.c3L
pcb.c3K
pcb.c3J
pcb.c3I
pcb.c3H
pcb.c3G
pcb.c3F
pcb.c3E
pcb.c3D
pcb.c3C
pcb.c3B
pcb.c3A
pcb.c3@
pcb.c3?main.c2�node.c3)%structures.h3%structures.h3%structures.h3%structures.h3%structures.h3main.c3main.c3main.c3main.c3main.c3main.c3main.c3main.c3 node.c3-node.c3,node.c3+node.c3*%structures.h3 %structures.h3� ] � |]&���rL����mL���fE
�
�
�
y
\
$	�	�	�	�	g	J	���}W9����uR,��s=��kO���k8��rN5	�|O���K��D���� � �       �9mallocpcb.c�8#pcbpcb.cpcb_t *pcb �7fgetspcb.c0�.%5removed_nodenode.c0node_t* removed_nodeS�-	3oget_and_remove_nodenode.c*node_t *get_and_remove_node(linkedlist_t** list){'�,3listnode.c*linkedlist_t** list�+mallocnode.c$)�*5llistnode.c$linkedlist_t* llist <�)	)Knew_linkedlistnode.c"linkedlist_t* new_linkedlist(){*�(/curr_nodenode.cnode_t* curr_nodeX�'		#�append_nodenode.cvoid append_node(linkedlist_t** pcb_list, node_t* new_node ){/�&;pcb_listnode.clinkedlist_t** pcb_list)�%/new_nodenode.cnode_t* new_node �$mallocnode.c
!�#'nodenode.c
node_t* node 5�"	Inew_nodenode.c	node_t *new_node(pcb_t *val) {�!!valnode.c	pcb_t *valH� %!%Elinkedlist_tLinkedListstructures.h$struct LinkedList { node_t * �!%LinkedListstructures.h"0�!%%headLinkedListstructures.h#node_t *head5�%7node_tNodestructures.hstruct Node { pcb_t *�%Nodestructures.h)�%#dataNodestructures.hpcb_t *data/�%/nextNodestructures.hstruct Node *next.�%-pcb_tPCBstructures.hstruct PCB { int�%PCBstructures.h#�%pidPCBstructures.hint pid5�%%-arrival_timePCBstructures.hint arrival_time9�)%1total_cpu_timePCBstructures.hint total_cpu_time5�%%-io_frequencyPCBstructures.hint io_frequency3�#%+io_durationPCBstructures.hint io_duration/�%'exec_timePCBstructures.hint exec_time/�%'wait_timePCBstructures.hint wait_time)�%#statePCBstructures.hchar* state&�-%
TERMINATED_STATEstructures.h#�'%
WAITING_STATEstructures.h �!%
EXEC_STATEstructures.h!�#%
READY_STATEstructures.h�%
NEW_STATEstructures.h&�
-%
__STRUCTURES_H__structures.h�	
__PCB_H__pcb.h�!
__NODE_H__node.h�!string_pcbmain.ci�#append_nodemain.cf#�3get_and_remove_nodemain.ce3�'9new_exec_nodemain.cenode_t* new_exec_node �!string_pcbmain.c^�#append_nodemain.cZ#�3get_and_remove_nodemain.cY3� '9new_wait_nodemain.cYnode_t* new_wait_node �!string_pcbmain.cP#�~3get_and_remove_nodemain.cN3�}'9new_term_nodemain.cNnode_t* new_term_node �|!string_pcbmain.cE�{#append_nodemain.cC#�z3get_and_remove_nodemain.cB5�y);new_ready_nodemain.cBnode_t* new_ready_node �x!string_pcbmain.c:�w#append_nodemain.c8#�v3get_and_remove_nodemain.c75�u);new_ready_nodemain.c7node_t* new_ready_node �t+load_csv_as_pcbmain.c.+�s#-load_resultmain.c.int load_result �r)new_linkedlistmain.c*3�q!?wait_llistmain.c*linkedlist_t *wait_llist �p)new_linkedlistmain.c'3�o!?exec_llistmain.c'linkedlist_t *exec_llist �n)new_linkedlistmain.c$5�m#Aready_llistmain.c$linkedlist_t *ready_llist �l)new_linkedlistmain.c!0�k;pcb_llistmain.c linkedlist_t *pcb_llist�jfclosemain.c�ifprintfmain.c�hfopenmain.c�gfclosemain.c�ffopenmain.c-�e#1output_filemain.cFILE* output_file #�d'o_filemain.cchar* o_file #�c'i_filemain.cchar* i_file 1�b)3curr_exec_timemain.cint curr_exec_time +�a#-system_timemain.cint system_time )�`!+system_runmain.cint system_run 4�_	Omainmain.cint main(int argc, char** argv) {�^argcmain.cint argc�]#argvmain.cchar** argv   
	!��6fgetspcb.c�5fopenpcb.c�4#filepcb.cFILE *file !�3)textpcb.cchar text[300]&�21delimpcb.cconst char* delim ^�1		+�load_csv_as_pcbpcb.c
int load_csv_as_pcb (char* textfile, linkedlist_t** pcb_llist) {%�0)textfilepcb.c
char* textfile0�/=pcb_llistpcb.c
linkedlist_t** pcb_llist   8 �����v`H2����~X9��iP8���fE
�
�
�
y
\
$	�	�	�	�	g	J	���}W9����uR,��s=��kO���k8��rN5	�|O���K                                                                                              0�.%5removed_nodenode.c0node_t* removed_nodeS�-	3oget_and_remove_nodenode.c*node_t *get_and_remove_node(linkedlist_t** list){'�,3listnode.c*linkedlist_t** list�+mallocnode.c$)�*5llistnode.c$linkedlist_t* llist <�)	)Knew_linkedlistnode.c"linkedlist_t* new_linkedlist(){*�(/curr_nodenode.cnode_t* curr_nodeX�'		#�append_nodenode.cvoid append_node(linkedlist_t** pcb_list, node_t* new_node ){/�&;pcb_listnode.clinkedlist_t** pcb_list)�%/new_nodenode.cnode_t* new_node �$mallocnode.c
!�#'nodenode.c
node_t* node 5�"	Inew_nodenode.c	node_t *new_node(pcb_t *val) {�!!valnode.c	pcb_t *valH� %!%Elinkedlist_tLinkedListstructures.h$struct LinkedList { node_t * �!%LinkedListstructures.h"0�!%%headLinkedListstructures.h#node_t *head5�%7node_tNodestructures.hstruct Node { pcb_t *�%Nodestructures.h)�%#dataNodestructures.hpcb_t *data/�%/nextNodestructures.hstruct Node *next.�%-pcb_tPCBstructures.hstruct PCB { int�%PCBstructures.h#�%pidPCBstructures.hint pid5�%%-arrival_timePCBstructures.hint arrival_time9�)%1total_cpu_timePCBstructures.hint total_cpu_time5�%%-io_frequencyPCBstructures.hint io_frequency3�#%+io_durationPCBstructures.hint io_duration/�%'exec_timePCBstructures.hint exec_time/�%'wait_timePCBstructures.hint wait_time)�%#statePCBstructures.hchar* state&�-%
TERMINATED_STATEstructures.h#�'%
WAITING_STATEstructures.h �!%
EXEC_STATEstructures.h!�#%
READY_STATEstructures.h�%
NEW_STATEstructures.h&�
-%
__STRUCTURES_H__structures.h�	
__PCB_H__pcb.h�!
__NODE_H__node.h�!string_pcbmain.ci�#append_nodemain.cf#�3get_and_remove_nodemain.ce3�'9new_exec_nodemain.cenode_t* new_exec_node �!string_pcbmain.c^�#append_nodemain.cZ#�3get_and_remove_nodemain.cY3� '9new_wait_nodemain.cYnode_t* new_wait_node �!string_pcbmain.cP#�~3get_and_remove_nodemain.cN3�}'9new_term_nodemain.cNnode_t* new_term_node �|!string_pcbmain.cE�{#append_nodemain.cC#�z3get_and_remove_nodemain.cB5�y);new_ready_nodemain.cBnode_t* new_ready_node �x!string_pcbmain.c:�w#append_nodemain.c8#�v3get_and_remove_nodemain.c75�u);new_ready_nodemain.c7node_t* new_ready_node �t+load_csv_as_pcbmain.c.+�s#-load_resultmain.c.int load_result �r)new_linkedlistmain.c*3�q!?wait_llistmain.c*linkedlist_t *wait_llist �p)new_linkedlistmain.c'3�o!?exec_llistmain.c'linkedlist_t *exec_llist �n)new_linkedlistmain.c$5�m#Aready_llistmain.c$linkedlist_t *ready_llist �l)new_linkedlistmain.c!0�k;pcb_llistmain.c linkedlist_t *pcb_llist�jfclosemain.c�ifprintfmain.c�hfopenmain.c�gfclosemain.c�ffopenmain.c-�e#1output_filemain.�Pfclosepcb.c-�Ofprintfpcb.c,�Nfopenpcb.c+,�M#1output_filepcb.c+FILE* output_file c�L		!�!string_pcbpcb.c*void string_pcb(char* o_file, pcb_t* pcb, int sys_time, char* old_state) {!�K%o_filepcb.c*char* o_file�J!pcbpcb.c*pcb_t* pcb#�I%sys_timepcb.c*int sys_time'�H+old_statepcb.c*char* old_state�Gfclosepcb.c&�F#append_nodepcb.c#�Enew_nodepcb.c" �D'nodepcb.c"node_t *node �Cstrtokpcb.c�Batoipcb.c�Astrtokpcb.c�@atoipcb.c�?strtokpcb.c�>atoipcb.c�=strtokpcb.c�<atoipcb.c�;strtokpcb.c�:atoipcb.c