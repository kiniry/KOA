package ie.koa;

/**
 * Decisions taken during the counting of votes.
 * 
 * @design It is necessary to be able to record any decision which might
 * influence the order in which votes are counted of transfered. 
 */
public class Decision {
	
/**
 * State value of decision to declare this candidate elected by
 * reaching the quota
 */	
	public final static byte ELECTBYQUOTA = 0;
	
/**
 * State value for decision to eliminate this candidate as the lowest
 * continuing candidate
 */	
	public final static byte EXCLUDE = 1;
	
/**
 * Default state value to indicate that no decision has been taken
 */	
	public final static byte NODECISION = 2;
	
/**
 * State value for decision to round up a fractional transfer of votes
 * to this candidate
 */	
	public final static byte ROUNDUPFRACTION = 3;
	
/**
 * State value for decision to distribute the surplus of this candidate
 */	
	public final static byte DISTRIBUTESURPLUS = 4;

/**
 * State value for the decision to deem this candidate elected as one
 * of the highest continuing candidates for the last remaining seats 
 */	
	public final static byte DEEMELECTED = 5;
	
/** Type of decision taken */
//@ public initially decisionTaken == NODECISION;
/*@ public invariant (decisionTaken == ELECTBYQUOTA) ||
  @   (decisionTaken == EXCLUDE) || (decisionTaken == NODECISION) || 
  @   (decisionTaken == ROUNDUPFRACTION) || (decisionTaken == DISTRIBUTESURPLUS)
  @   || (decisionTaken == DEEMELECTED);
  @ public constraint (\old(decisionTaken) != NODECISION) ==>
  @ decisionTaken == \old(decisionTaken);
  @*/
	public byte decisionTaken;
	
/** Candidate to which the decision applied */
//@ public invariant (decisionTaken != NODECISION) ==> 0 < candidateID;
//@ public invariant candidateID != Ballot.NONTRANSFERABLE;
/*@ public constraint (decisionTaken != NODECISION) ==>
  @   candidateID == \old(candidateID);
  @*/
//@ public initially candidateID == 0;	
	public long candidateID;
	
/** Round of counting at which decision was taken */
//@ public invariant 0 <= atCountNumber;
//@ public initially atCountNumber == 0;
/*@ public constraint (decisionTaken != NODECISION) ==>
  @   atCountNumber == \old(atCountNumber);
  @*/
	public long atCountNumber;
	
/** Indicates if lots were drawn to make this decision */
/*@ public invariant (decisionTaken == ELECTBYQUOTA) ==>
  @   chosenByLot == false;
  @ public constraint (decisionTaken != NODECISION) ==>
  @   chosenByLot == \old(chosenByLot);  
  @*/
	// @bug -Patrick - cannot use (== \old)in that context.Invalid types (boolean and void) in equality comparison
	public boolean chosenByLot;
	
/**	Default constructor */
/*@ public normal_behavior
  @   ensures decisionTaken == NODECISION;
  @   ensures atCountNumber == 0;
  @   ensures candidateID == 0;
  @   ensures chosenByLot == false;
  @*/
	public /*@ pure @*/ Decision(){
		decisionTaken = NODECISION;
		atCountNumber = 0;
		candidateID = 0;
		chosenByLot = false;	
	}
	
	public void checkInvariants() {
		;
	}
}
