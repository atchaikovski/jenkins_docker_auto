import hudson.model.JDK
import hudson.tools.InstallSourceProperty
import hudson.tools.ZipExtractionInstaller

def descriptor = new JDK.DescriptorImpl();
def List<JDK> installations = []

javaTools=[
           ['name':'jdk8', 'url':'file:/var/jenkins_home/downloads/jdk-8u131-linux-x64.tar.gz', 'subdir':'jdk1.8.0_131'],
           ['name':'jdk7', 'url':'file:/var/jenkins_home/downloads/openjdk-7u75-b13-linux-x64-18_dec_2014.tar.gz', 'subdir':'jdk1.7'],
           ['name':'jdk9', 'url':'file:/var/jenkins_home/downloads/openjdk-9.0.1_-linux-x64_bin.tar.gz', 'subdir':'jdk1.9']
          ]

javaTools.each { javaTool ->

    println("Setting up tool: ${javaTool.name}")

    def installer = new ZipExtractionInstaller(javaTool.label as String, javaTool.url as String, javaTool.subdir as String);
    def jdk = new JDK(javaTool.name as String, null, [new InstallSourceProperty([installer])])
    installations.add(jdk)

}
descriptor.setInstallations(installations.toArray(new JDK[installations.size()]))
descriptor.save()