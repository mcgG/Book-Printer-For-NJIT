<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>printer</title>

  <link rel="stylesheet" href="style/index.css">

  <!-- 新 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <!-- 可选的Bootstrap主题文件（一般不用引入） -->
  <link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.min.css">
  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="bootstrap/js/jquery-1.11.1.js"></script>
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

      <div id="center">
          <!-- Button trigger modal -->
          <button type="button" id="button-1" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#login">
              <h4>Login</h4>
          </button>
          <br />
          <button type="button" id="button-2" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#join">
              <h4>Join&nbsp;&nbsp;</h4>
          </button>
      </div>

      <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="loginLabel">Login</h4>
                  </div>
                  <div class="modal-body">
                      <form method="post" action="/servlet/ValidLoginServlet">
                          <div style="margin: auto;" class="input-group input-group-lg">
                              <input type="text" class="form-control" placeholder="UCID" aria-describedby="sizing-addon1" name="UCID" value="kz82">
                          </div>
                          <br />
                          <div style="margin: auto;" class="input-group input-group-lg">
                              <input type="password" class="form-control" placeholder="Password" aria-describedby="sizing-addon1" name="password" value="123">
                          </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                              <button type="submit" class="btn btn-primary">Login</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      </div>

      <div class="modal fade" id="join" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="joinLabel">Join Us</h4>
                  </div>
                  <div class="modal-body">
                      <form method="post" action="/servlet/RegisterServlet">
                          <div class="input-group input-group-lg">
                              <input type="text" class="form-control" name="username" placeholder="Username" aria-describedby="sizing-addon1">
                          </div>
                          <p></p>
                          <div class="input-group input-group-lg">
                              <input type="text" class="form-control" name="UCID" placeholder="UCID" aria-describedby="sizing-addon1">
                          </div>
                          <p></p>
                          <div class="input-group input-group-lg">
                              <input type="text" class="form-control" name="major" placeholder="Major" aria-describedby="sizing-addon1">
                          </div>
                          <p></p>
                          <div class="input-group input-group-lg">
                              <input type="password" class="form-control" name="password" placeholder="Password" aria-describedby="sizing-addon1">
                          </div>
                          <p></p>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                              <button type="submit" class="btn btn-primary">Register Now</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      </div>

</body>
</html>