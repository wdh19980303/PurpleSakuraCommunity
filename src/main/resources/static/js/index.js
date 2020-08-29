$(function () {

    pageHelp(1, 2, 0)

})


function pageHelp(current, pageSize, creator) {
    $.get("/article/list/" + current + "/" + pageSize + "/" + creator + "", "", function (pageDateSet) {

        var articles = pageDateSet.pageList;

        // 数据展示部分
        if (articles != null) {

            var list = "";

            var imgSrc = "";


            for (let i = 0; i < articles.length; i++) {
                $.ajaxSettings.async = false;
                $.get("/article/creatorImg/" + articles[i].creator + "", {}, function (ImgSrc) {
                    imgSrc = ImgSrc.toString();
                })
                $.ajaxSettings.async = true;


                list += "<div class=\"media\">\n" +
                    "              <div class=\"media-left media-middle\">\n" +
                    "                <a href=\"#\">\n" +
                    "                  <img class=\"media-object\" src=\"" + imgSrc + "\" alt=\"Loading...\">\n" +
                    "                </a>\n" +
                    "              </div>\n" +
                    "              <div class=\"media-body\">\n" +
                    "                   <a href='/article/complex/" + articles[i].id + "'><h4 class=\"media-heading\">" + articles[i].title + "</h4></a>\n" +
                    "                   <span class='articleCount'>内容简介:&nbsp;" + articles[i].introduction + "</span><br>" +
                    "                   <span class='articleCount'>浏览数:&nbsp;" + articles[i].viewCount + "&nbsp;回复数:&nbsp;" + articles[i].commentCount + "&nbsp;点赞数:&nbsp;" + articles[i].likeCount + "</span><br>" +
                    "                   <span class='articleCount'>创建时间:&nbsp;" + articles[i].gmtCreate + "</span><br>" +
                    "                   <span class='articleCount'>标签:&nbsp;" + articles[i].tag + " </span><br>" +
                    "              </div>\n" +
                    "            </div>\n" +
                    "          </div>"


            }


            $("#articles").html(list.toString())


            //分页部分
            var start
            var end
            var index
            var pageIndex = ""
            var previous
            var next

            previous = pageDateSet.currentPage - 1;
            next = pageDateSet.currentPage + 1;

            if (previous == 0) {
                previous = 1
            }

            if (next > pageDateSet.pages) {
                next = pageDateSet.pages
            }

            if (pageDateSet.pages < 10) {
                start = 1
                end = pageDateSet.pages
            } else {
                start = pageDateSet.currentPage - 4
                end = pageDateSet.currentPage + 5

                if (start < 0) {
                    start = 1;
                    end = 10;
                }

                if (end > pageDateSet.pages) {
                    end = pageDateSet.pages;
                    start = end - 9;
                }
            }


            pageIndex += " <li>\n" +
                "                  <a href=\"javascript:pageHelp(" + previous + ", " + pageDateSet.pageSize + " , " + creator + ")\" aria-label=\"Previous\">\n" +
                "                    <span aria-hidden=\"true\">&laquo;</span>\n" +
                "                  </a>\n" +
                "                </li>"

            for (index = start; index <= end; index++) {
                if (index == pageDateSet.currentPage) {
                    pageIndex += " <li class='active'> <a href=\"javascript:pageHelp(" + index + ", " + pageDateSet.pageSize + ","+ creator+ ")\">" + index + "</a></li>"
                } else {
                    pageIndex += " <li ><a href=\"javascript:pageHelp(" + index + ", " + pageDateSet.pageSize + " , " + creator + ")\">" + index + "</a></li>"

                }

            }

            pageIndex += "<li>\n" +
                "                  <a href=\"javascript:pageHelp(" + next + ", " + pageDateSet.pageSize + ", " + creator + " )\" aria-label=\"Next\">\n" +
                "                    <span aria-hidden=\"true\">&raquo;</span>\n" +
                "                  </a>\n" +
                "                </li>"

            $("#pages").html(pageIndex)

            $("#pageInfo").html("<span class='articleCount'>总条数:" + pageDateSet.total + "&nbsp;&nbsp;&nbsp;共计:" + pageDateSet.pages + " 页 </span><br>")
        }


    })
}






