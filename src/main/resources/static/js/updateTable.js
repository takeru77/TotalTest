document.addEventListener('click', function(event) {
	const clickedElement = event.target;
	console.log(clickedElement);
	const clickedParentElement = clickedElement.parentNode;
	console.log(clickedParentElement);
			
	// tagNameは大文字を返す
	if (clickedParentElement.tagName === 'TD') {
		const previousSibling = clickedParentElement.previousElementSibling;
		console.log(previousSibling);
		const previousSibling2 = previousSibling.previousElementSibling;
		console.log(previousSibling2);
		// ここで、previousSibling2からIDを取得する処理を追加
		const purposeId = previousSibling2.textContent;
		document.getElementById('submitTaskId').value = purposeId;
		document.getElementById('submitTask').submit();
	}
});