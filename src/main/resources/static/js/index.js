$(function () {
    $.get("/indexList", "", function (articles) {
        if (articles != null) {

            var list = "";
            var lists= "";
            var imgSrc = "";

            for (let i = 0; i < articles.length; i++) {
                $.ajaxSettings.async = false;
                $.get("/articleImg/"+ articles[i].creator +"",{},function (ImgSrc) {
                   imgSrc = ImgSrc.toString();
                })
                $.ajaxSettings.async = true;



                list += "<div class=\"media\">\n" +
                    "              <div class=\"media-left media-middle\">\n" +
                    "                <a href=\"#\">\n" +
                    "                  <img class=\"media-object\" src=\""+imgSrc+"\" alt=\"Loading...\">\n" +
                    "                </a>\n" +
                    "              </div>\n" +
                    "              <div class=\"media-body\">\n" +
                    "                   <h4 class=\"media-heading\">"+ articles[i].title +"</h4>\n" +
                    "                   <span class='articleCount'>内容简介:&nbsp;"+ articles[i].introduction +"</span><br>" +
                    "                   <span class='articleCount'>浏览数:&nbsp;"+ articles[i].viewCount+"&nbsp;回复数:&nbsp;"+ articles[i].commentCount +"&nbsp;点赞数:&nbsp;"+ articles[i].likeCount +"</span><br>" +
                    "                   <span class='articleCount'>创建时间:&nbsp;"+ articles[i].gmtCreate +"</span><br>" +
                    "                   <span class='articleCount'>标签:&nbsp;"+ articles[i].tag + " </span><br>" +
                    "              </div>\n" +
                    "            </div>\n" +
                    "          </div>"


            }


            $("#articles").html(list.toString())
        }
    })

})




