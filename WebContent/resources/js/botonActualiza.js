window.onload = function() {iniciar();}

function iniciar(){
	alert("Elige el usuario a actualizar");
	document.getElementById("actualiza").disabled = "enabled";
	document.getElementById("busca").onclick = activaActualiza;
	
}

function activaActualiza(){
	document.getElementById("actualiza").disabled = "disabled";
}