/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.db2.snapshot.lock;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import io.debezium.annotation.ConnectorSpecific;
import io.debezium.connector.db2.Db2Connector;
import io.debezium.connector.db2.Db2ConnectorConfig;
import io.debezium.snapshot.spi.SnapshotLock;

@ConnectorSpecific(connector = Db2Connector.class)
public class ExclusiveSnapshotLock implements SnapshotLock {

    @Override
    public String name() {
        return Db2ConnectorConfig.SnapshotLockingMode.EXCLUSIVE.getValue();
    }

    @Override
    public void configure(Map<String, ?> properties) {

    }

    @Override
    public Optional<String> tableLockingStatement(Duration lockTimeout, String tableId) {

        return Optional.of("SELECT * FROM " + tableId + " WHERE 0=1 WITH CS");
    }
}
