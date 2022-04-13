package core.runner.editor;

import core.model.config.Config;
import core.processor.OnlyofficeEditorProcessor;
import core.processor.post.OnlyofficeEditorPostProcessor;
import core.processor.pre.OnlyofficeEditorPreProcessor;
import core.runner.OnlyofficeEditorRunner;
import exception.OnlyofficeProcessAfterRuntimeException;
import exception.OnlyofficeProcessBeforeRuntimeException;
import exception.OnlyofficeRunnerRuntimeException;
import exception.OnlyofficeUploaderRuntimeException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class OnlyofficeSequentialEditorRunner implements OnlyofficeEditorRunner {
    private final OnlyofficeEditorProcessor editorProcessor;
    private final Map<String, OnlyofficeEditorPreProcessor> preProcessors;
    private final Map<String, OnlyofficeEditorPostProcessor> postProcessors;

    /**
     *
     * @param request
     * @throws OnlyofficeRunnerRuntimeException
     * @throws OnlyofficeProcessBeforeRuntimeException
     * @throws OnlyofficeProcessAfterRuntimeException
     * @throws OnlyofficeUploaderRuntimeException
     * @throws IOException
     */
    public Config run(ConfigRequest request) throws OnlyofficeRunnerRuntimeException, OnlyofficeProcessBeforeRuntimeException, OnlyofficeProcessAfterRuntimeException, OnlyofficeUploaderRuntimeException, IOException {
        if (request == null)
            throw new OnlyofficeRunnerRuntimeException("Expected to get a ConfigRequest instance. Got null");

        preProcessors.forEach((name, processor) -> {
            processor.processBefore();
            processor.processBefore(request);
        });

        this.editorProcessor.process(request);

        postProcessors.forEach((name, processor) -> {
            processor.processAfter();
            processor.processAfter(request);
        });

        return request.getConfig();
    }
}
