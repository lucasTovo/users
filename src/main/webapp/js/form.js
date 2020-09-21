let displayResults = ( results ) => {
  let ul = document.getElementById( 'responseOut' );
  ul.innerHTML = '';
  for( item of results.results ){
    let li = document.createElement( 'li' );
    li.innerHTML = item.name;
    ul.appendChild( li );
  } //end for
} // end displayResults
let sendCreate = () =>{
  let name = document.getElementById( 'nm' ).value;
  let email = document.getElementById( 'em1' ).value;
  let password = document.getElementById( 'pw1' ).value;
  axios.post( 'http://localhost:9080/orion-users-service/users/api/v1/create/?name=' + name + '&email=' + email + '&password=' + password)
    .then( function( response ){
      displayResults( response.data );
    });
} // end 

let sendForgot = () =>{
  let email = document.getElementById( 'em1' ).value;
  axios.post( 'http://localhost:9080/orion-users-service/users/api/v1/forgotPass/?email=' + email)
    .then( function( response ){
      displayResults( response.data );
    });
} // end 

let sendRetrieve = () =>{
  let password = document.getElementById( 'pw1' ).value;

  var urlnow = window.location.href;
  let par = (new URL(urlnow)).searchParams;
  let hash = par.get("hash");

  axios.post( 'http://localhost:9080/orion-users-service/users/api/v1/changePass/?hash=' + hash + '&password=' + password)
    .then( function( response ){
      displayResults( response.data );
    });
} // end 

function redirect() {
  window.location.replace("index.html");
  return false;
}

