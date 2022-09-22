<!DOCTYPE HTML>
<html>
  <head>
    <title> Thank you for join </title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
  </head>

  <body id="php_join">
    <h2> 회원가입을 축하드립니다. </h2>
    <p class="sentence"> 회원가입 정보는 아래와 같습니다. </p>

    <?php
      $id = $_GET["id"];
      $pwd = $_GET["pwd"];
      $mail = $_GET["p_email"];
      $phone = $_GET["p_tel"];
    ?>

    <p> 아이디 : <?php print $id; ?> </p>
    <p> 비밀번호 : <?php print $pwd; ?> </p>
    <p> 이메일 : <?php print $mail; ?> </p>
    <p> 전화번호 : <?php print $phone; ?> </p>
  </body>
</html>
