import groovy.util.logging.Slf4j
@Grab(group='ch.qos.logback', module='logback-classic', version='1.2.3')

@Slf4j
class Script {
    def run(args) {
        def config = new Config(args)

        // This is where the logic of the script belongs

        log.info  "username=" + config.username
        log.info  "password=" + config.password
        println config
    }
}
new Script().run(args)

/**
 * The section below can be extracted into a separate file.
 * But I prefer scripts to be contained in one file.
 *
 */
@GrabConfig(systemClassLoader=true)
@Grab('info.picocli:picocli:4.2.0')
import groovy.cli.picocli.CliBuilder
@Slf4j
class Config extends java.util.LinkedHashMap {
    public Config(String[] args) {
        // Define and parse the command-line arguments
        def cli = new CliBuilder(name:'MyScript')
        cli.h(type: Boolean, 'help')
        cli.c(type: String, 'config file')
        cli.e(type: String, 'environment: dev | test')
        cli.u(type: String, 'username')
        cli.p(type: String, 'password')
        def options = cli.parse(args)
        if (options.h) {
            println cli.usage()
            System.exit(0)
        }

        this.configFilename = options.c ?: "config.txt"
        this.environment = options.e ?: "dev"
        log.debug "Environment = " + this.environment

        def config = new ConfigSlurper(this.environment).parse(new File(this.configFilename).toURI().toURL())
        // new URL('xyz.com').text

        // Copy the config items into this object
        config.each{k,v ->
            this[k] = v
        }

        // Override config items with commandline options
        this.username = options.u ?: this.username
    }
}