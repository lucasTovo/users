// grab the things we need
const signupForm = document.getElementById('signup-form');
const nameInput  = signupForm.querySelector('input[name=name]');
const emailInput = signupForm.querySelector('input[name=email]');

// listen for the submit event
signupForm.addEventListener('submit', processSignupForm);
function processSignupForm(e) {
  e.preventDefault();

  const name = nameInput.value;
  const email = emailInput.value;

  console.log({ name, email });
  alert('Processing!');
}
