const a = [-1,3,-9,2,2,-1,2,-1,-5]
function kurs(array){
    let buyDate = 0; // 1
	let sellDate = 0; // 1
	let sum = 0; // 1
	// Loops through the days to buy stocks
	for(let i = 0; i < array.length; i++){ // 1 + 2n
		let temp = 0 // 1
		// Loops through the days to sell stocks
		for(let j = i + 1; j < array.length; j++){ // 1 + 2n
			temp += array[j]; // 1
			if (temp > sum){ // 1
				sum = temp; // 1
				buyDate = i + 1;  // 1
				sellDate = j + 1; // 1
			}
		}
	}
	return `Buy: ${buyDate}, sell: ${sellDate}, profit: ${sum}`
} // Total time complexity n^2
let randomArray = num =>{
	return Array.from({length: num}, () => Math.floor(Math.random() * 10) - 5);
}
console.log(kurs(a));
function time(param){
	let start = new Date();
	let runder = 0;
	let tid;
	let slutt;
	do{
		kurs(param);	
		slutt = new Date();
		++runder;
	} while (slutt.getTime() - start.getTime() < 1000);
	tid = (slutt.getTime() - start.getTime()) / runder;
	return `Millisekund pr. runde: ${tid}`
}
console.log(time(randomArray(10000)));
