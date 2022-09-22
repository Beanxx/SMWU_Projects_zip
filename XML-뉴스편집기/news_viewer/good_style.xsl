<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <HTML>
      <HEAD>
        <style type="text/css">
          body {font-family:함초롬바탕;margin-left:500px;margin-right:500px;}
          h1 {font-style:italic;color:#050099;}
          p {color:#008000;}
          h2 {font-style:bold;color:#4374D9;}

        </style>
      </HEAD>
      <BODY>
        <h1> NEWS </h1>
        <br/>
        <xsl:apply-templates select="//info"/>
        <xsl:apply-templates select="//title"/>
        <xsl:apply-templates select="//img"/>
        <xsl:apply-templates select="//contents"/>

     </BODY>
    </HTML>
  </xsl:template>

  <xsl:template match="info">
    <p> 출판사 : <xsl:apply-templates select="publisher"/> </p>
    <p> 기자 이름 : <xsl:apply-templates select="editor/name"/> </p>
    <p> 기자 이메일 : <xsl:apply-templates select="editor/email"/> </p>
    <p> 기사 포스팅 date :  <xsl:apply-templates select="date"/> </p> <br/>
  </xsl:template>

  <xsl:template match="publisher">
    <xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="editor">
    <xsl:value-of select="name"/>
    <xsl:value-of select="email"/>
  </xsl:template>
  <xsl:template match="date">
    <xsl:value-of select="."/>
  </xsl:template>

  <xsl:template match="title">
  <h2>  <xsl:value-of select="."/> </h2><br/> <br/>
  </xsl:template>

  <xsl:template match="img">
    <img>
      <xsl:attribute name="src">
        <xsl:value-of select="@file"/>
      </xsl:attribute>
    </img>
  </xsl:template>

  <xsl:template match="contents">
    <br/><br/> <xsl:value-of select="."/> <br/>
  </xsl:template>



</xsl:stylesheet>
