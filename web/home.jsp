<%--
  Created by IntelliJ IDEA.
  User: zhaokaihao
  Date: 12/5/15
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*,action.*,bean.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>printer</title>

    <link rel="stylesheet" href="style/home.css">

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="style/jquery.dataTables.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="bootstrap/js/jquery-1.11.1.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="home.js"></script>
    <script src="js/jquery.dataTables.min.js"></script>

</head>

<%
    String UCID = (String)session.getAttribute("UCID");
    UserInfoBean userInfoBean = GetInfo.getUserInfo(UCID);
    String username = userInfoBean.getUserName();
    String major = userInfoBean.getMajor();
    List<MajorBean> allMajorList = GetInfo.getAllMajor();
    List<ProfessorBean> allProfessorList = GetInfo.getAllProfessor();
    List<BookBean> myAllBooks = GetInfo.getBookByUCID(UCID);
%>

<body>

<div id="banner">
    Welcome, ${sessionScope.UCID}
</div>

<div id="content">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#books" aria-controls="Books" role="tab" data-toggle="tab">My Books</a></li>
        <li role="presentation"><a href="#search" aria-controls="search" role="tab" data-toggle="tab">Search</a></li>
        <li role="presentation"><a href="#upload" aria-controls="upload" role="tab" data-toggle="tab">Settings</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane fade in active" id="books">

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add">
                Add a Book
            </button>

            <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="loginLabel">Login</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="/servlet/FileUploadServlet" enctype="multipart/form-data">
                                <div class="input-group input-group-lg">
                                    <input id="file" type="file" class="form-control" placeholder="file" aria-describedby="sizing-addon1" name="file">
                                </div>
                                <br />
                                <div class="input-group input-group-lg">
                                    <input id="fileName" type="text" class="form-control" placeholder="BookName" aria-describedby="sizing-addon1"name="bookName">
                                </div>
                                <div class="input-group input-group-lg">
                                    <select class="form-control" name="major">
                                        <option value="all">All Majors</option>
                                        <% for(int i=0; i<allMajorList.size(); i++) { %>
                                        <option value="<%=allMajorList.get(i).getMajorCode()%>">
                                            <%=allMajorList.get(i).getMajorName()%>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="input-group input-group-lg">
                                    <select class="form-control" name="courseLevel">
                                        <option value="all">All Level</option>
                                        <option value="undergraduate">undergraduate</option>
                                        <option value="graduate">graduate</option>
                                    </select>
                                </div>
                                <div class="input-group input-group-lg">
                                    <select class="form-control" name="professor">
                                        <option value="all">All Professors</option>
                                        <% for(int i=0; i<allProfessorList.size(); i++) { %>
                                        <option value="<%=allProfessorList.get(i).getpID()%>">
                                            <%=allProfessorList.get(i).getpName()%>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Add</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <h2>My Books:</h2>
            <p></p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>BookName</th>
                    <th>Major</th>
                    <th>Professor</th>
                    <th>Course Level</th>
                    <th>Shared Times</th>
                    <th>Print</th>
                    <th>Share</th>
                </tr>
                </thead>
                <tbody>
                <% for(int i=0; i<myAllBooks.size(); i++) { %>
                <tr>
                    <td><a href="<%=myAllBooks.get(i).getFilePath()%>" target="_blank"><%= myAllBooks.get(i).getBookName() %></a></td>
                    <td><%= myAllBooks.get(i).getMajor() %></td>
                    <td><%= myAllBooks.get(i).getProfessor() %></td>
                    <td><%= myAllBooks.get(i).getCourseLevel() %></td>
                    <td><%= myAllBooks.get(i).getSharedTimes() %></td>
                    <td>
                        <button type="button" id="<%= myAllBooks.get(i).getId() %>" class="getPrintPanel" data-toggle="modal" data-target="#printPanel">
                            Print
                        </button>
                    </td>
                    <td>
                        <button id="<%= myAllBooks.get(i).getId() %>" type="button" class="share-btn">Share</button>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <div class="modal fade" id="printPanel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Print</h4>
                        </div>
                        <div class="modal-body">
                            <table class="table table-striped">
                                <h2>Splited Files:</h2>
                                <p></p>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>File Name</th>
                                            <th>Print</th>
                                        </tr>
                                    </thead>
                                    <tbody id="replaceHere">
                                    </tbody>
                                </table>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div>


        <div role="tabpanel" class="tab-pane fade" id="search">
            <div id="search-content">
                <div class="select">
                    <label for="major">Select Major:</label>
                    <select id="major" class="form-control">
                        <option value="all">All Majors</option>
                        <% for(int i=0; i<allMajorList.size(); i++) { %>
                        <option value="<%=allMajorList.get(i).getMajorCode()%>">
                            <%=allMajorList.get(i).getMajorName()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="select">
                    <label for="course-level">Select Course Level:</label>
                    <select id="course-level" class="form-control">
                        <option value="all">All Level</option>
                        <option value="undergraduate">undergraduate</option>
                        <option value="graduate">graduate</option>
                    </select>
                </div>

                <div class="select">
                    <label for="professor">Select Professor:</label>
                    <select id="professor" class="form-control">
                        <option value="all">All Professors</option>
                        <% for(int i=0; i<allProfessorList.size(); i++) { %>
                        <option value="<%=allProfessorList.get(i).getpID()%>">
                            <%=allProfessorList.get(i).getpName()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <br />
                <div class="select">
                    <label for="course-level"> </label>
                    <button id="search-btn" type="button" class="btn btn-primary">Search</button>
                </div>

                <div id="context">
                    <h2>Results:</h2>
                    <p></p>

                    <table id="mainTable" class="display" width="100%"></table>

                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="upload">.ghjjh..</div>
</div>
</div>
</body>
</html>
