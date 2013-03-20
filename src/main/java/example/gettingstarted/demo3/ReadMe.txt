JAXB Extensions

Overview

This example will build upon the lessons learned in the previous example (JAXB Customizations), and demonstrate how MOXy extensions can be used to further customize the XML output.

Using MOXy Extensions

Some of the MOXy extensions are available through EclipseLink annotations, others require programmatic changes to the underlying metadata. The EclipseLink annotation @XmlPath is used to specify path based mappings.