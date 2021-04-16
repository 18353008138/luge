var vm = new Vue({
    el:'#logindiv',
    data:{
        ub:{}
    },
    methods:{
        getLogin:function () {
            var _this = this;
            axios.post("user/getLogin.do",_this.ub).then(function (response) {
                location.href="pages/main.html";
            });
        }
    }
});
