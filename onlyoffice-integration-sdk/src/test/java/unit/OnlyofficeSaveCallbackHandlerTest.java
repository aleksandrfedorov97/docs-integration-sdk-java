package unit;

import base.handler.OnlyofficeSaveCallbackHandler;
import base.uploader.OnlyofficeDefaultCallbackUploaderRunner;
import core.model.callback.Callback;
import core.registry.OnlyofficeCallbackHandler;
import core.registry.OnlyofficeCallbackRegistry;
import core.uploader.OnlyofficeUploaderRunner;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OnlyofficeSaveCallbackHandlerTest {
    private final OnlyofficeCallbackRegistry mockRegistry = Mockito.mock(OnlyofficeCallbackRegistry.class);
    private final OnlyofficeUploaderRunner<Callback> mockUploaderRunner = Mockito.mock(OnlyofficeDefaultCallbackUploaderRunner.class);
    private final OnlyofficeCallbackHandler saveCallbackHandler = new OnlyofficeSaveCallbackHandler(mockRegistry, mockUploaderRunner);

    @Test
    public void runHandleCallback() {
        assertDoesNotThrow(() -> saveCallbackHandler.handle(Callback
                .builder()
                .status(2)
                .build()
        ));
    }
}
