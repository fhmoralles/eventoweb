function inputCurrency_corrigeValor(element, symbol, maxLength) {

	var value = element.value;
	if (value == "") {
		value = '0';
	}
	value = value.replace(/R|\$|\.|\D|\s|,/gi, "");
	while(value.charAt(0) == '0'){
		value = value.substring(1,value.length);
	}
	if (value.length < 3) {
		var i = value.length;
		for (; i < 3; i++) {
			value = "0" + value;
		}
	}
	var inteira = value.substring(0, value.length - 2);
	var retorno;
	var count = inteira.length % 3 == 0? 3 : inteira.length % 3;
	retorno = inteira.substring(0, count);
	while (count < inteira.length) {
		retorno += "." + inteira.substring(count, count + 3);
		count += 3;
	}
	retorno += "," + value.substring(value.length - 2, value.length);

	element.value = symbol + " " + retorno;
	if(	element.value.length > maxLength){
		element.value = element.value.substring(0,maxLength);
		inputCurrency_corrigeValor(element, symbol,maxLength);
	}
};

