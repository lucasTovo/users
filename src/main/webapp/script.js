
    if(!localStorage.getItem('password')){
      localStorage.setItem('password', 'pass');
    }

    function changePassBtnClick(){
      localStorage.setItem('password', document.getElementById('changePass').value);
      alert('Password changed');
    }

    function loginBtnClick(){
      if(document.getElementById('login').value == localStorage.getItem('password')){
        alert('Correct Login');
      }else{
        alert('Wrong Password');
      }
    }
 