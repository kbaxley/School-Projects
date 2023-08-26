.global _start

.data

.text

mod:
	push {ip, lr}
	
	mov r2, r0        // save a copy of r0 (numerator)
	
	div:
		sub r2, r2, r3  // subtract denominator from numerator
		cmp r2, #0      // compare numerator to 0
		blt done        // if less than (remainder) then move to next numerator
		beq nextDen     // if no remainder then move to next denominator
		b div           // keep subtracting until at or under zero
		
	nextDen:
		cmp r3, #2      // if denominator reaches 20 then we found it
		beq foundOne    // execute found protocol beep boop beep
		sub r3, #1      // else increase denominator
		b mod           // then rinse and repeat
		
	foundOne:
		mov r1, #0      // set r1 to true
	
	done:             // mod quits if any remainder appears
		
	pop {ip, pc}
	
_start:
	push {ip, lr}
	
	ldr r0, =2520     // start w/ 2520 
	mov r1, #1        // boolean to track if found

	while: 
		
		cmp r1, #0      // if r1 is zero then found == true
		beq found       // quit program if found
		add r0, r0, #20 // else increase r0 by 20
		mov r3, #19
		bl mod          // keep looking
		bl while        // dont stop til found
		
	found:
	
	pop {ip, pc}
