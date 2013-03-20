JAXB Customization

Overview

This example will build upon the lessons learned in the previous example (The Basics), and demonstrate how JAXB annotations can be used to customize the XML output.

Customizing a Type

In the previous example (The Basics) we required a supplementary object to serve as the root of our model. When can use JAXB annotations to specify which classes in our domain model can serve as roots, this is done using the @XmlRootElement annotation. Now that this annotation has been added we no longer require the use of the JAXBElement class.

By default the order in which the XML elements are marshalled out is dependent on the implementation. We can control the order using the propOrder property on the JAXB annotation @XmlType.

