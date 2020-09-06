<!DOCTYPE HTML>
<html>
  <head>
    <title> 설문 조사 </title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
  </head>

  <body id="php_survey">
    <h4><strong> 응답해주신 설문 조사 결과는 아래와 같습니다.<strong></h4>
    <?php
      $s = $_GET["sex"];
      $o = $_GET["old"];
      $i = $_GET["hobby_introduce"];
      $e = $_GET["hobby_experience"];
      $p = $_GET["hobby_plan"];
      $p_etc = $_GET["etc"];
    ?>

    <p> 성별 :
    <?php
      if($s=="male") print "남자";
      elseif($s=="female") print "여자";
    ?>
    </p>

    <p> 나이: <?php print $o; ?></p>

    <p> 흥미로웠던 취미 정보 :
    <?php
      if($_GET["hobby_introduce"]=="on") print "나의 취미 소개(Hobby Intrdouce) &nbsp";
      if($_GET["hobby_experience"]=="on") print "취미에 대한 나의 경험들(Hoobby Experience) &nbsp";
      if($_GET["hobby_plan"]=="on") print "취미에 대한 앞으로의 계획 (Hobby Plan) &nbsp";
    ?>
    </p>
  </body>
</html>
