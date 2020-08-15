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
   
  new Vue({
    el: "#app",
    data: {
      auth: ''
    },
    beforeRouteEnter(to, from, next) {
        if(Object.keys(to.query).length !== null) { //if the url has query (?query)
          next(vm => {
           vm.auth = to.query.auth
         })
      }
      next()
    }
  })