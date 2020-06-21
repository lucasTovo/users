const app = new Vue({
	el: '#signup-form',
	data: {
	  name: '',
	  email: ''
	},
	methods: {
	  processForm: function() {
		console.log({ name: this.name, email: this.email });
		alert('Processing');
	  }
	}
  });