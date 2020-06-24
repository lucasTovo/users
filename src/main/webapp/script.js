var check = function() {
  var bt = document.getElementById('btSubmit');
   
    if (document.getElementById('pw1').value ==
      document.getElementById('pw2').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'matching';
      if(pw1.value != '' && pw2.value != ''){
        bt.disabled = false;
      }
      
    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'not matching';
      
        bt.disabled = true;
      
    }
  }


//   function manage(email) {
//     var bt = document.getElementById('btSubmit');
//     if (email.value == '') {
//         bt.disabled = true;
//     }
    
// } 

   

const app = new Vue({
	el: '#signup-form',
	data: {
	  email: '',
	  password: ''
    }
    
  });