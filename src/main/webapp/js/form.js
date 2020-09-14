let displayResults = ( results ) => {
  let ul = document.getElementById( 'responseOut' );
  ul.innerHTML = '';
  for( item of results.results ){
    let li = document.createElement( 'li' );
    li.innerHTML = item.name;
    ul.appendChild( li );
  } //end for
} // end displayResults
let searchNow = () =>{
  let name = document.getElementById( 'nm' ).value;
  let email = document.getElementById( 'em1' ).value;
  let password = document.getElementById( 'pw1' ).value;
  axios.post( 'http://localhost:9080/orion-users-service/users/api/v1/create/?name=' + name + '&email=' + email + '&password=' + password)
    .then( function( response ){
      displayResults( response.data );
    });
} // end searchNow

let validNow = () =>{
  let hash = document.getElementById( 'hash' ).value;
  axios.post( 'http://localhost:9080/orion-users-service/users/api/v1/confirmHash/?hash=' + hash)
    .then( function( response ){
      displayResults( response.data );
    });
} // end searchNow

function redirect() {
  window.location.replace("index.html");
  return false;
}

