<?xml version="1.0" encoding="ISO-8859-1"?><xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.opentravel.org/OTA/2003/05" version="1.0"><!-- Duplicate --><xsl:template match="//xs:complexType[@name='TimeSpanType'][xs:attribute[@name='End']/xs:simpleType]"><xs:complexType name="TimeSpanTypeNotif">    <xsl:apply-templates /></xs:complexType></xsl:template><!-- Method Name collision - Description changes type here - NOTE: This is probably a JiBX bug --><xsl:template match="//xs:element[@name='OTA_VehRateRuleRS']//xs:extension[@base='VehicleType']/xs:sequence/xs:element[@name='Description']"><xs:element name="Description" type="xs:string" minOccurs="0">	<xs:annotation>		<xs:documentation xml:lang="en">The description of this vehicle.</xs:documentation>	</xs:annotation></xs:element></xsl:template><!-- This is a duplicate definition that is never used (in GroundCancelRS) --><xsl:template match="//xs:complexType[@name='VehicleCancelRSAdditionalInfoType'][../xs:element[@name='OTA_GroundCancelRS']]"><xs:complexType name="GroundCancelRSAdditionalInfoType">    <xsl:apply-templates /></xs:complexType>                   </xsl:template><!-- Move this this definition (from HotelReservations) --><xsl:template match="//xs:complexType[@name='ServiceRPHsType'][../xs:complexType[@name='ServicesType']]"><!-- Moved to HotelCommonTypes --></xsl:template><!-- Remove this circular include and move this definition (to HotelCommonTypes) --><xsl:template match="//xs:include[@schemaLocation='OTA_HotelReservation.xsd'][../xs:simpleType[@name='AvailabilityStatusType']]">	<xs:include schemaLocation="OTA_Profile.xsd"/>	<xs:complexType name="ServiceRPHsType">		<xs:annotation>			<xs:documentation xml:lang="en">A collection of unsigned integers that reference the RPH (Reference Place holder) attribute in the Service object. The ServiceRPH attribute in the Service object is an indexing attribute that identifies the services attached to a specific guest or to the reservation.</xs:documentation>		</xs:annotation>		<xs:sequence>			<xs:element name="ServiceRPH" maxOccurs="unbounded">				<xs:annotation>					<xs:documentation xml:lang="en">This is a reference placeholder used as an index for a service to be associated with this stay</xs:documentation>				</xs:annotation>				<xs:complexType>					<xs:attribute name="RPH" type="RPH_Type" use="optional">						<xs:annotation>							<xs:documentation xml:lang="en">Provides a unique reference to the service.</xs:documentation>						</xs:annotation>					</xs:attribute>					<xs:attribute name="IsPerRoom" type="xs:boolean" use="optional">						<xs:annotation>							<xs:documentation xml:lang="en">Defines whether this is for each room of a RoomStay</xs:documentation>						</xs:annotation>					</xs:attribute>				</xs:complexType>			</xs:element>		</xs:sequence>	</xs:complexType></xsl:template><!-- TODO Fix this - This is probably a JiBX bug (found in VehModifyRS.xsl) --><xsl:template match="//xs:list[@itemType='TransactionStatusType']"><xs:list itemType="xs:string">    <xsl:apply-templates /></xs:list>                   </xsl:template><!-- Get rid of these fuzzy namespaces --><xsl:template match="//xs:schema[@targetNamespace='http://www.opentravel.org/OTA/2003/05/alpha']"><xs:schema xmlns="http://www.opentravel.org/OTA/2003/05" xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://www.opentravel.org/OTA/2003/05" elementFormDefault="qualified" version="1.000" id="OTA2013B">    <xsl:apply-templates /></xs:schema></xsl:template><!-- Declare namespace if not --><xsl:template match="//xs:schema[not(@targetNamespace='http://www.opentravel.org/OTA/2003/05')]">  <xsl:copy>    <xsl:for-each select="@*">      <xsl:attribute name="{name()}">        <xsl:value-of select="."/>      </xsl:attribute>    </xsl:for-each>      <xsl:attribute name="targetNamespace">http://www.opentravel.org/OTA/2003/05</xsl:attribute>  	<xsl:apply-templates />  </xsl:copy></xsl:template><xsl:template match="*">  <xsl:copy>    <xsl:for-each select="@*">      <xsl:attribute name="{name()}">        <xsl:value-of select="."/>      </xsl:attribute>    </xsl:for-each>  	<xsl:apply-templates />  </xsl:copy></xsl:template></xsl:stylesheet>