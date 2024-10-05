async function checkNumber() {
    const number = document.getElementById('numberInput').value;
    const algorithm = document.getElementById('algorithmSelect').value;
    const containerBody = document.getElementById('containerBody');

    const requestBody = {
        number: parseInt(number),
        algorithm: algorithm
    };

    const response = await fetch('/api/find', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
    });

    const result = await response.json();
    const container = document.getElementById('result');
    const timeElapsed = document.getElementById('timeElapsed');

    container.innerHTML = '';

    if (number < 2 || number > 100000) {
        document.getElementById('ErrorMessage').innerText = "Number inserted is either too small or too big!";
        containerBody.style.display = 'none';
        timeElapsed.innerText = '';
    } else {
        document.getElementById('ErrorMessage').innerText = "";
        result.listOfPrimeNumbers.forEach(number => {
            const div = document.createElement('div');
            div.className = 'number-item';
            div.innerText = number;
            container.appendChild(div);
        });
        timeElapsed.innerText = "It took: " + result.timeElapsed / 1e+9 + " seconds to generate";
        containerBody.style.display = 'flex';
    }
}