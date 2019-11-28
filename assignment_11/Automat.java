class Automat {

	private char[] inputAlphabet;
	private int[] acceptState;
	private int[][] nextState;

	public Automat(char[] inputAlphabet, int[] acceptState, int[][] nextState){
		this.inputAlphabet = inputAlphabet;
		this.acceptState = acceptState;
		this.nextState = nextState;
	}

	private boolean ifExists(char[] input){
		for(int i = 0; i < input.length; i++){
			boolean exists = false;
			for(int j = 0; j < inputAlphabet.length; j++){
				innerloop:
				if(input[i] == inputAlphabet[j]){
					exists = true;
					break innerloop;
				}
			}
			if(!exists) return false;
		}
		return true;
	}

	private boolean ifInAcceptState(int position){
		boolean accepted = false;
		for(int i = 0; i < acceptState.length; i++){
			if(position == acceptState[i]){
				accepted = true;
			}
		}
		return accepted;
	}

	private int getAlphabetIndex(char input){
		for(int i = 0; i < inputAlphabet.length; i++){
			if(input == inputAlphabet[i]){
				return i;
			}
		} 
		return -1;
	}

	public boolean checkInput(char[] input){
		if(!ifExists(input)) return false;

		int position = 0;
		for(int i = 0; i < input.length; i++){
			boolean found = false;
			for(int j = 0; j < nextState[position].length; j++){
				if(getAlphabetIndex(input[i]) == j && !found){
					position = nextState[position][j];
					found = true;
				}
			}
		}
		return ifInAcceptState(position);
	}

	private static char[] stringToCharArray(String str){
		char[] arr = new char[str.length()];
		for(int i = 0; i < arr.length; i++){
			arr[i] = str.charAt(i);
		}
		return arr;
	}

	public static void main(String[] args){

		char[] inputAlphabet = {'0', '1'};
		int[] acceptState = {3};
		int[][] nextState = {{2, 1}, {1, 1}, {2, 3}, {3, 1}};

		Automat aut = new Automat(inputAlphabet, acceptState, nextState);

		String input = "010";
		System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));

		input = "111";
		System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));

		input = "010110";
		System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));

		char[]inputAlphabet2 = {'a', 'b'};
		int[][]nextState2 = {{1, 2}, {4, 3}, {3, 4}, {3, 3}, {4, 4}};

		aut = new Automat(inputAlphabet2, acceptState, nextState2);

		input = "abbb";
        System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));

        input = "aaab";
        System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));

        input = "babab";
        System.out.println(input + ": " + aut.checkInput(stringToCharArray(input)));
	}
}
