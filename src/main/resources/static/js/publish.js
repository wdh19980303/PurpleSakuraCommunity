$(function () {
    var articleEdit = new Vue({
        el: "#articleEdit",
        data: {
            article: {
                title: "",
                introduction: "",
                description: "",
                tag: "",
            },
            error: {
                message: "",
                errorDisplay: false
            },


        },
        methods: {
            submitArticle: function (event) {
                event.preventDefault()
                then_vue = this
                var formData = new FormData(event.target)
                axios.post("/pushArticle", formData).then(function(response){

                    then_vue.article.title = response.data.model.title
                    then_vue.article.description = response.data.model.description
                    then_vue.article.addTag = response.data.model.tag
                    then_vue.article.introduction = response.data.model.introduction

                    // alert(response.data.error)
                    if (response.data.error) {
                        then_vue.error.errorDisplay = true
                        then_vue.error.message = response.data.error

                    }
                }).catch(function () {
                    alert("出错")
                })
            },



        }
    })

})