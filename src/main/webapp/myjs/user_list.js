var vm = new Vue({
    el:'#userdiv',
    data:{
        userlist:[],
        pageNum:1,
        pageSize:5,
        seluser:{},
        page:{},
        entity:{},
        deptids:[],
        dlist:[]
    },
    methods:{
        getUserListConn:function () {
            var _this=this;
            axios.post("../user/getUserListConn.do?pageNum="+_this.pageNum+"&pageSize="+_this.pageSize,_this.seluser).then(function (request) {
                _this.pageNum=request.data.currentPage;
                _this.userlist=request.data.list;
                _this.pageSize=request.data.pageSize;
                _this.page=request.data;
            });
        },
        paging:function (pageNum) {
            this.pageNum = pageNum;
            this.getUserListConn();
        },
        toUserDept:function (id) {
            var _this=this;
            axios.get("../user/toUserDept.do?id="+id).then(function (response) {
                _this.entity=response.data;
                _this.dlist=response.data.dlist;
                _this.deptids = response.data.deptids;
                document.getElementById("userdeptdiv").style.display="block";
            });
        },
        saveUserDept:function () {
            var _this=this;
            axios.post("../user/saveUserDept.do?id="+_this.entity.id,_this.deptids).then(function (response) {
                if (response.data.flag){
                    _this.getUserListConn();
                    document.getElementById("userdeptdiv").style.display="none";
                }else{
                    alert(response.msg);
                }
            })
        },
        toUserPost:function (id) {
            var _this = this;
            axios.get('../user/toUserPost.do?id='+id).then(function (response) {
                _this.entity = response.data;
                _this.dlist = response.data.dlist;
                document.getElementById("userpostdiv").style.display="block";
            });
        },
        saveUserPost:function() {
            this.entity.dlist=this.dlist;
            var _this=this;
            axios.post("../user/saveUserPost.do",_this.entity).then(function (response) {
                if (response.data.flag){
                    alert(response.data.msg);
                    document.getElementById("userpostdiv").style.display="none"
                }else {
                    alert(response.data.msg);
                }
            })
        }
    }
});
vm.getUserListConn();
