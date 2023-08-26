.global _start

.data

n: .word 9              // declare global variable n

.text

root:
	push {ip, lr}

	mov r0, #1            // store x into r0

	mainLoop:
		
		mov r3, r0          // store oldX into r3
	
		// Enter while loop to divide x/n
    ---------------------------------
		
    mov r4, #0          // initialize index
		mov r5, r2          // store copy of n (remainder)
		
    divOne: 
		
			sub r5, r5, r3    // subract x from n
			add r4, r4, #1    // count how many times
			cmp r5, #0        // subtract until remainder is less than/equal to zero
			ble doneDiv
			b divOne 

		doneDiv: 
			
      add r0, r4, r0    // store quotient in r0 
			lsr r0, #1        // divide by using logic shift right
			b done            // branch to done


	done: 
		
    cmp r0, r3          // compare x to oldX
		bne mainLoop        // branch to loop one if not even
	

	pop {ip, pc}
	
_start:
	push {ip, lr}

	ldr r1, =n            // load address of n into r1
	ldr r2, [r1]          // load value of n into r2
	
	bl root               // call subroutine
	

	pop {ip, pc}
