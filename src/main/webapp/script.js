var check = function() {
    if (document.getElementById('pw1').value ==
      document.getElementById('pw2').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'matching';
    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'not matching';
    }
  }


  function manage(email) {
    var bt = document.getElementById('btSubmit');
    if (email.value != '') {
        bt.disabled = false;
    }
    else {
        bt.disabled = true;
    }
} 

function managepw1(pw1) {
    var bt = document.getElementById('btSubmit');
    if (pw1.value != '') {
        bt.disabled = false;
    }
    else {
        bt.disabled = true;
    }
}    

function managepw2(pw2) {
    var bt = document.getElementById('btSubmit');
    if (pw2.value != '') {
        bt.disabled = false;
    }
    else {
        bt.disabled = true;
    }
}    

const app = new Vue({
	el: '#signup-form',
	data: {
	  email: '',
	  password: ''
    }
    
  });