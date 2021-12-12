
let caretPosition = 32;     // In pixels
const userInput = document.getElementById('user-input');
const caret = document.getElementById('blink');

userInput.addEventListener('keydown', function(event) {
    if(event.key === 'Enter') {
        // Move text to div above textarea
        event.preventDefault();
        const text = document.getElementById('text');
        input = userInput.value;
        input = input.replace(/\s/g, '&nbsp;');
        // Empty input corner cases
        text.innerHTML += (input === '') ? '<p>&nbsp;</p>' : '<p>' + input + '</p>';
        // Reset values
        userInput.value = '';
        caret.style.transform = 'translateX(32px) translateY(-46px)';
        caretPosition = 32;
    }
    if(caretPosition !== 32 && event.key === 'ArrowLeft') {
        stopBlinking();
        // All these __ * 8 because ubuntu mono is 8px wide
        caretPosition = (userInput.selectionStart * 8) + 24
        moveCaret(caretPosition);
    }
    else if(caretPosition < userInput.value.length * 8 + 32 && event.key === 'ArrowRight') {
        stopBlinking();
        caretPosition = (userInput.selectionStart * 8) + 40;
        moveCaret(caretPosition);
    }
});

userInput.addEventListener('click', function() {
    caretPosition = (userInput.selectionStart * 8) + 32;
        moveCaret(caretPosition);
});

userInput.addEventListener("input", function() {
    stopBlinking();
    let input = userInput.value;
    input = input.replace(/\s/g, '&nbsp;');
    caretPosition = (userInput.selectionStart * 8) + 32;
    moveCaret(caretPosition);
});

const terminal = document.getElementById('terminal');
terminal.addEventListener('click', function() {
    userInput.focus();
})

function moveCaret(pixels) {
    caret.style.transform = 'translateX(' + pixels + 'px) translateY(-46px)';
}

function stopBlinking() {
    caret.id = 'solid';
    setTimeout(function() {
        caret.id = 'blink';
    }, 250);
}