$(function () {
    var choose = new Vue({
        el: "#choose",
        data: {

            /*个人文章*/
            /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
            isAction: [false, false, false, false, false],
            isBadge: [false, false, false, false, false],
            sectionMark: -1,
            sectionNames: ["我的文章", "个人资料", "最新消息", "关注中心", "审核查询"],
            selectReq: ["article", "profile", "replies", "attention", "check"],

            /*最新消息*/
            /************************************************************/
            repliesCount: 0,


        },

        mounted:function() {
            replies(this,"mounted")
            pageHelp(1, 2, -1)
        },
        methods: {
            chooseAction: function (action, mark) {
                var then = this
                then.sectionMark = mark
                for (var i = 0; i < then.isAction.length; i++) {
                    if (mark === i) {
                        this.$set(then.isAction, i, true)
                    } else {
                        this.$set(then.isAction, i, false)
                    }
                }

                if (action) {
                    if (action === "article") {

                    }

                    switch (action) {

                        /*我的文章*/
                        case then.selectReq[0] :
                            pageHelp(1, 2, -1)
                            break

                        /*个人资料*/
                        case then.selectReq[1] :
                            break

                        /*最新回复*/
                        /***************************************/
                        case then.selectReq[2] :
                            replies(then,"replies")
                            break

                        /*关注中心*/
                        case then.selectReq[3] :
                            break

                        /*审核查询*/
                        case then.selectReq[4] :
                            break


                    }

                }

            }
        }
    })


})




function replies(then,error) {
    axios.get("/profile/replies").then(function (response) {
        // alert(response.data.model.count)
        if (response.data.model.count > 0) {
            then.isBadge[2] = true
            then.repliesCount = response.data.model.count
        }
    }).catch(function () {
        alert(error)
    })
}