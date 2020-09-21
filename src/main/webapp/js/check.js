var checkCreate = function() {
    var bt = document.getElementById('btSubmit');
    var check1 = false;
     var check2 = false;

      if (document.getElementById('pw1').value ==
        document.getElementById('pw2').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'fill in all fields';
        if(pw1.value != '' && pw2.value != ''){

          check1 = true;
        }
            } else {
              document.getElementById('message').style.color = 'red';
              document.getElementById('message').innerHTML = 'not matching';
              
                bt.disabled = true;
              
            }

          if (document.getElementById('em1').value ==
          document.getElementById('em2').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'fill in all fields';
          if(em1.value != '' && em2.value != ''){
            
            check2 = true;
          }
                } else {
                  document.getElementById('message').style.color = 'red';
                  document.getElementById('message').innerHTML = 'not matching';
                  
                    bt.disabled = true;
                }

                  
              if(check1 && check2 == true){
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'maching!';
                    bt.disabled = false;
                  }}

var checkForgot = function() {
    var bt = document.getElementById('btSubmit');
    var check = false;


          if (document.getElementById('em1').value ==
          document.getElementById('em2').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'fill in all fields';
          if(em1.value != '' && em2.value != ''){
            
            check = true;
          }
                } else {
                  document.getElementById('message').style.color = 'red';
                  document.getElementById('message').innerHTML = 'not matching';
                  
                    bt.disabled = true;
                }

                  
              if(check == true){
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'maching!';
                    bt.disabled = false;
                  }}

var checkRetrieve = function() {
    var bt = document.getElementById('btSubmit');
    var check = false;


          if (document.getElementById('pw1').value ==
          document.getElementById('pw2').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'fill in all fields';
          if(pw1.value != '' && pw2.value != ''){
            
            check = true;
          }
                } else {
                  document.getElementById('message').style.color = 'red';
                  document.getElementById('message').innerHTML = 'not matching';
                  
                    bt.disabled = true;
                }

                  
              if(check == true){
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'maching!';
                    bt.disabled = false;
                  }}