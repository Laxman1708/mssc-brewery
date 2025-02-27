/*
 *  Copyright (c) 2021 Caterpillar Inc. All Rights Reserved.
 *
 *  This work contains Caterpillar Inc.'s unpublished
 *  proprietary information which may constitute a trade secret
 *  and/or be confidential. This work may be used only for the
 *  purposes for which it was provided, and may not be copied
 *  or disclosed to others. Copyright notice is precautionary
 *  only, and does not imply publication.
 */

package com.cat.digital.platform.common.aws.metrics;

import io.micrometer.core.instrument.Tag;

public enum AggregatedStatus {

    INFORMATIONAL("1xx"),
    SUCCESS("2xx"),
    REDIRECTION("3xx"),
    CLIENT_ERROR("4xx"),
    SERVER_ERROR("5xx"),
    UNKNOWN("Unknown");

    private final Tag tag;

    AggregatedStatus(String name) {
        this.tag = Tag.of("status", name);
    }

    /**
     * Tag value for enum.
     */
    public Tag asTag() {
        return tag;
    }

    /**
     * Get enum value by status code.
     * For example it groups all 400-499 into a single 4xx to avoid creation individual dimentions in Cloudwatch.
     * @param status HTTP status
     * @return AggregatedStatus value
     */
    public static AggregatedStatus forStatus(int status) {
        if (status >= 100 && status < 200) {
            return INFORMATIONAL;
        } else if (status >= 200 && status < 300) {
            return SUCCESS;
        } else if (status >= 300 && status < 400) {
            return REDIRECTION;
        } else if (status >= 400 && status < 500) {
            return CLIENT_ERROR;
        } else if (status >= 500 && status < 600) {
            return SERVER_ERROR;
        }
        return UNKNOWN;
    }

}