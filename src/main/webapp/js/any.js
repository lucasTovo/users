new Vue({
  el: '#app',
  data() {
    return {
      users: [],
      loading: false,
      submitting: false,
      newUser: '',
    }
  },
  methods: {
    fetchUsers() {
      this.loading = true;
      this.users = [];

      axios.get('http://localhost:9080/orion-users-service/users/api/v1/read/')
        .then((response) => {
          const data = response.data;
          this.users = data;
          this.loading = false;
        });
    },
    addUser() {
      this.submitting = true;
      axios.post('http://localhost:9080/orion-users-service/users/api/v1/create/', {
        name: this.newUser,
        email: this.newUser,
        password: this.newUser
      })
        .then((response) => {
          const data = response.data;
          this.users.push(data);
          this.newUser = '';
          this.submitting = false;
        });
    }
  }
})


