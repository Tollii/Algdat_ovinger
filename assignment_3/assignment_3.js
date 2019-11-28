// Swap places of two array elements
const swap = (arr, i, j) => {
	const x = arr[j];
	arr[j] = arr[i]
	arr[i] = x;
}

// Bublesort of an integer array
const bubblesort = arr => {
	const n = arr.length;
	for(let i = 0; i < n - 1; i++){
		for(let j = 0; j < n - i - 1; j++){
			if(arr[j] > arr[j + 1]){
				swap(arr, j, j + 1);
			}
		}
	}
}

const insertionsort = (arr, v = 0, h = arr.length - 1) => {
	for(let j = v; j <= h; j++){
		let swap = arr[j];
		let i = j - 1;
		while(j >= v && arr[i] > swap){
			arr[i + 1] = arr[i];
			i--;
		}
		arr[i + 1] = swap;
	}
}

const median3sort = (t, v, h) => {
	const m = Math.floor((v + h) / 2);
	if(t[v] > t[m]) swap(t, v, m);
	if(t[m] > t[h]){
		swap(t, m, h);
		if(t[v] > t[m]) swap(t, v, m);
	}
	return m;
}

const splitt = (t, v, h) => {
	let iv, ih;
	const m = median3sort(t, v, h);
	const p = t[m];
	swap(t, m, h - 1);
	for(iv = v, ih = h - 1; ;){
		while(t[++iv] < p);
		while(t[--ih] > p);
		if(iv >= ih) break;
		swap(t, iv, ih);
	}
	swap(t, iv, h - 1);
	return iv;
}

const quicksort = (t, v = 0, h = arr.length - 1) => {
	if(h - v > 400) {
		const delepos = splitt(t, v, h);
		quicksort(t, v, delepos - 1);
		quicksort(t, delepos + 1, h);
	} else 	insertionsort(arr);

}

const randomArray = max => {
	let arr = []
	for(let i = 0; i < max; i++){
		arr.push(Math.floor(Math.random() * (100 + 1)));
	}
	return arr;
}

const time = (func, param) => { 
	let start = new Date();
	let runder = 0;
	let tid;
	let slutt;
	do{
		func(param);
		slutt = new Date();
		++runder;
	} while(slutt.getTime() - start.getTime() < 1000);
	tid = (slutt.getTime() - start.getTime()) / runder;
	return `Millisekund pr. runde: ${tid}`
}

// -----------------------------------------------------------------------------------------------

let arr = randomArray(100000);
let checksum = 0;
for(let i = 0; i < arr.length; i++){
	checksum += arr[i];
}

const param = arr;
//console.log(arr);
console.log(time(quicksort, arr));
//console.log(arr);

for(let i = 0; i < arr.length - 2; i++){
	if(arr[i+1] >= arr[i]){

	} else {
		console.log("Error");
	}
}

let checkAgainst = 0;
for(let i = 0; i < arr.length; i++){
	checkAgainst += arr[i];
}
if(checkAgainst == checksum){
	console.log("Same checksum");
}