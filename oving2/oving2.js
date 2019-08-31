let powerLin = (num, exp) => {
	return (exp === 1) ? num : num * powerLin(num, exp -1);
}
let powerLog = (num, exp) => {
	if (exp === 1) return num;
	if ((exp % 2) !== 0) return num * powerLog(num * num, (exp - 1) / 2);
	return powerLog(num * num, exp / 2);
}
function time(func, param){ 
	let start = new Date();
	let runder = 0;
	let tid;
	let slutt;
	let result;
	do{
		result = func(param[0], param[1]);
		slutt = new Date();
		++runder;
	} while(slutt.getTime() - start.getTime() < 1000);
	tid = (slutt.getTime() - start.getTime()) / runder;
	console.log(result);
	return `Millisekund pr. runde: ${tid}`
}
console.log(time(powerLin, [1.001, 5000]));
console.log(time(powerLog, [1.001, 5000]));
console.log(time(Math.pow, [1.001, 5000]));
