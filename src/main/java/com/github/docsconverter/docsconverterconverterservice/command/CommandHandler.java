package com.github.docsconverter.docsconverterconverterservice.command;

import java.io.IOException;

public interface CommandHandler<T, I, O> {
    T execute(I input, O output) throws IOException;
}
