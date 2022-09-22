<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
  <HTML>
    <HEAD>
      <style type="text/css">
        body {font-family:함초롬바탕;}
        table, tr, th, td {padding:10px;}
        th {font-style:italic;}
      </style>
    </HEAD>
    <BODY>
      <table border="1">
        <tr>
          <th> 기사 제목 </th>
          <td> <xsl:value-of select = "article/content/title"/> </td>
        </tr>

        <tr>
          <th> 기자 </th>
          <td> <xsl:value-of select = "article/info/editor/name"/> </td>
        </tr>

        <tr>
          <th> 기사 요약 </th>
          <td> <xsl:value-of select = "article/content/summary"/> </td>
        </tr>
      </table>
    </BODY>
  </HTML>
  </xsl:template>
</xsl:stylesheet>
