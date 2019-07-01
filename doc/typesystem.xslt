<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
				xmlns:rs="http://uima.apache.org/resourceSpecifier" 
				xmlns:aif="http://www.averbis.com">
	<xsl:output method="text" media-type="text/xml" encoding="utf-8" indent="no" />
	
	<xsl:function name="aif:cleanAndEscapeUserInput">
		<xsl:param name="inString"/>
		<xsl:variable name="handledSpaces" select="normalize-space($inString)"/>
		<xsl:choose>
			<xsl:when test="string-length($handledSpaces) = 0">
				<xsl:value-of select="'-'"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="handledPipes" select="replace($handledSpaces, '\|', '\\|')"/>
				<xsl:value-of select="concat('pass:specialchars[', $handledPipes, ']')"/>
			</xsl:otherwise>
		</xsl:choose>		
	</xsl:function>
	
	<xsl:template match="rs:typeSystemDescription">
		<xsl:variable name="shortname">
			<xsl:call-template name="substring-after-last">
				<xsl:with-param name="string" select="rs:name" />
				<xsl:with-param name="delimiter" select="'.'" />
			</xsl:call-template>
		</xsl:variable>
=== <xsl:value-of select="$shortname" /><xsl:text>&#xa;</xsl:text>
*<xsl:value-of select="rs:name" />* <xsl:text>&#xa;</xsl:text><xsl:text>&#xa;</xsl:text>

<xsl:value-of select="aif:cleanAndEscapeUserInput(rs:description)" /><xsl:text>&#xa;</xsl:text>
				<xsl:text>
		
*Maven Coordinates*

[source,xml]
----
&lt;dependency&gt;
	&lt;groupId&gt;${project.groupId}&lt;/groupId&gt;
	&lt;artifactId&gt;${project.artifactId}&lt;/artifactId&gt;
	&lt;version&gt;${project.version}&lt;/version&gt;
&lt;/dependency&gt;
----
		
		</xsl:text>
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template name="substring-after-last">
		<xsl:param name="string" />
		<xsl:param name="delimiter" />
		<xsl:choose>
			<xsl:when test="contains($string, $delimiter)">
				<xsl:call-template name="substring-after-last">
					<xsl:with-param name="string" select="substring-after($string, $delimiter)" />
					<xsl:with-param name="delimiter" select="$delimiter" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$string" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="rs:imports[count(*)&gt;0]">
*Imports* <xsl:text>&#xa;</xsl:text>
		<xsl:for-each select="rs:import">
			<xsl:call-template name="import" />
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="import">
* <xsl:value-of select="@name" /> <xsl:value-of select="@location" /><xsl:text>&#xa;</xsl:text>
	</xsl:template>

	<xsl:template match="rs:typeDescription">
			<xsl:variable name="typeshortname">
				<xsl:call-template name="substring-after-last">
					<xsl:with-param name="string" select="rs:name" />
					<xsl:with-param name="delimiter" select="'.'" />
				</xsl:call-template>
			</xsl:variable>
==== <xsl:value-of select="$typeshortname" /><xsl:text>&#xa;</xsl:text>
*Full Name*: `<xsl:value-of select="rs:name" />`<xsl:text>&#xa;</xsl:text>
*Description*: <xsl:value-of select="aif:cleanAndEscapeUserInput(rs:description)" /><xsl:text>&#xa;</xsl:text>
*Parent Type*: `<xsl:value-of select="rs:supertypeName" />`<xsl:text>&#xa;</xsl:text>

			<xsl:if test="rs:features">
.Features<xsl:text>&#xa;</xsl:text>
[width="100%",cols="1,1a,1a,1",options="header",]<xsl:text>&#xa;</xsl:text>
|=======================================================================<xsl:text>&#xa;</xsl:text>
|*Name* |*Range* |*Element Type* |*Multiple References Allowed*<xsl:text>&#xa;</xsl:text>
				<xsl:apply-templates />
|=======================================================================<xsl:text>&#xa;</xsl:text>
			</xsl:if>
	</xsl:template>


	<xsl:template match="rs:featureDescription">
|`*<xsl:value-of select="rs:name" />*`| `<xsl:value-of select="rs:rangeTypeName" />`
		<xsl:choose>
			<xsl:when test="rs:elementType">
|`<xsl:value-of select="rs:elementType" />`
			</xsl:when>
			<xsl:otherwise>|</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="rs:multipleReferencesAllowed">
|`<xsl:value-of select="rs:multipleReferencesAllowed" />`
			</xsl:when>
			<xsl:otherwise>|</xsl:otherwise>
		</xsl:choose>
<xsl:text>&#xa;</xsl:text>
| 3+|_Description_: <xsl:value-of select="aif:cleanAndEscapeUserInput(rs:description)" /><xsl:text>&#xa;</xsl:text>			
	</xsl:template>

	<xsl:template match="*">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="@*|text()">
	</xsl:template>

</xsl:stylesheet> 