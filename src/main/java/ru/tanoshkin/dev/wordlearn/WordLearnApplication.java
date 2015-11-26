package ru.tanoshkin.dev.wordlearn;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by a.tanoshkin on 26.11.2015.
 */
public class WordLearnApplication extends Application<WordLearnConfiguration> {
    public static void main(String[] args) throws Exception {
        new WordLearnApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<WordLearnConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(WordLearnConfiguration configuration,
                    Environment environment) {
        final WordLearnResource resource = new WordLearnResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
