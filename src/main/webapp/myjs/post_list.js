var vm=new Vue({
   el:'#postdiv',
   data:{
       postlist:[],
       pageNum:1,
       pageSize:5,
       page:{},
       selpost:{},
       meunlist:[]
   },
    methods:{
        getPostListConn:function () {
            var _this=this;
            axios.post("../post/getPostListConn.do?pageNum="+_this.pageNum+"&pageSize="+_this.pageSize,_this.selpost).then(function (request) {
                _this.pageNum=request.data.currentPage;
                _this.postlist=request.data.list;
                _this.page=request.data;
                _this.pageSize=request.data.pageSize;
            });
        },
        paging:function (pageNum) {
            this.pageNum=pageNum;
            this.getPostListConn();
        },
        toPostMeun:function(id){
          var _this=this;
          axios.get("../post/getMeunListById.do?id="+id).then(function (response) {
              _this.meunlist=response.data;
              testaa(response.data,id);
              document.getElementById("nodes").style.display="block";
          })
        },
        savePostMeun:function (postid,ids) {
            var _this=this;
            axios.post("../post/savePostMeun.do?postid="+postid,ids).then(function (response) {
                if(response.data.flag){
                    alert(response.data.msg);
                    document.getElementById("nodes").style.display="none";
                }else{
                    alert(response.data.msg);
                }
            })
        }
    }
});
vm.getPostListConn();