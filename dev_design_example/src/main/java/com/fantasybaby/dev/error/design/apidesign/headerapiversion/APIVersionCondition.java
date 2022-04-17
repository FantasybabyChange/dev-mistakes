package com.fantasybaby.dev.error.design.apidesign.headerapiversion;

import lombok.Getter;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class APIVersionCondition implements RequestCondition<APIVersionCondition> {

    @Getter
    private String apiVersion;
    @Getter
    private String headerKey;

    public APIVersionCondition(String apiVersion, String headerKey) {
        this.apiVersion = apiVersion;
        this.headerKey = headerKey;
    }

    /**
     * 和另外的请求匹配条件进行合并，具体合并逻辑由实现提供
     *
     * @param other 其他
     * @return {@link APIVersionCondition}
     */
    @Override
    public APIVersionCondition combine(APIVersionCondition other) {
        return new APIVersionCondition(other.getApiVersion(), other.getHeaderKey());
    }

    /**
     * 得到匹配条件
     * 检查当前请求匹配条件和指定请求request是否匹配，如果不匹配返回null
     * 如果匹配，生成一个新的请求匹配条件，该新的请求匹配条件是当前请求匹配条件
     * 针对指定请求request的剪裁。
     * 举个例子来讲，如果当前请求匹配条件是一个路径匹配条件，包含多个路径匹配模板，
     * 并且其中有些模板和指定请求request匹配，那么返回的新建的请求匹配条件将仅仅
     * 包含和指定请求request匹配的那些路径模板。
     *
     *
     *
     * @param request 请求
     * @return {@link APIVersionCondition} 如果当前条件的匹配结果不为空，则说明当前条件是能够匹配上的，如果返回值为空，则说明其不能匹配
     */
    @Override
    public APIVersionCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader(headerKey);
        return apiVersion.equals(version) ? this : null;
    }

    /**
     * 针对指定的请求对象request比较两个请求匹配条件。
     * 该方法假定被比较的两个请求匹配条件都是针对该请求对象request调用了
     * #getMatchingCondition方法得到的，这样才能确保对它们的比较
     * 是针对同一个请求对象request，这样的比较才有意义(最终用来确定谁是
     * 更匹配的条件)。
     *
     * @param other   其他
     * @param request 请求
     * @return int
     */
    @Override
    public int compareTo(APIVersionCondition other, HttpServletRequest request) {
        return 0;
    }
}
