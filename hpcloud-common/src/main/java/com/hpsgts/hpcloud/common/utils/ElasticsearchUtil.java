package com.hpsgts.hpcloud.common.utils;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * @author 黄朴（Herper.Plain）
 * @Date 2017/12/20 下午4:39
 * @Company 上海宏鹿信息技术服务有限公司
 */
public class ElasticsearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchUtil.class);
    /**
     * http://192.168.85.63:9200/ sports-log
     **/
    private static final String HOST_NAME = PropertiesUtil.get("elasticsearch.hostName",false);
    private static final String CLUSTER_NAME = PropertiesUtil.get("elasticsearch.clusterName",false);
    private static int PORT = Integer.parseInt(PropertiesUtil.get("elasticsearch.port",false));
    private static TransportClient client;

    static {//初始化TransportClient
        Settings settings = Settings.builder()
                //设置elasticesearch的集群名称
                .put("cluster.name", CLUSTER_NAME).build();

        client = new PreBuiltTransportClient(settings);
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST_NAME), PORT));
            ClusterHealthResponse healths = client.admin().cluster().prepareHealth().get();
            String clusterName = healths.getClusterName();
            int numberOfDataNodes = healths.getNumberOfDataNodes();
            int numberOfNodes = healths.getNumberOfNodes();
            logger.info("elasticsearch init success .TransportClient clusterName:【{}】,numberOfDataNodes:【{}】,numberOfNodes:【{}】.", clusterName, numberOfDataNodes, numberOfNodes);
            for (ClusterIndexHealth health : healths.getIndices().values()) {
                String index = health.getIndex();
                int numberOfShards = health.getNumberOfShards();
                int numberOfReplicas = health.getNumberOfReplicas();
                ClusterHealthStatus status = health.getStatus();
                logger.info("current elasticsearch index 【{}】 status numberOfShards:【{}】,numberOfReplicas:【{}】,status:【{}】.", index, numberOfShards, numberOfReplicas, status);
            }
        } catch (UnknownHostException e) {
            logger.error("init elasticsearch address error.");
            e.printStackTrace();
            close();
        }
    }

    /**
     * 初始化TransportClient
     *
     * @return
     */
    public static TransportClient getinstance() {
        return client;
    }

    /**
     * 创建一个索引
     *
     * @param indexName 索引名
     */
    public static CreateIndexResponse createIndex(String indexName) {
        try {
            CreateIndexResponse indexResponse = client
                    .admin()
                    .indices()
                    .prepareCreate(indexName)
                    .get();
            // true表示创建成功
            logger.info("create index【{}】is {}", indexName, indexResponse.isAcknowledged());
            return indexResponse;
        } catch (ElasticsearchException e) {
            e.printStackTrace();
            close();
        }
        return null;
    }

    public static PutMappingResponse createMapping(XContentBuilder builder, String type, String... indices) {
        //创建mapping请求对象
        PutMappingRequest putMappingRequest = Requests.putMappingRequest(indices).source(builder).type(type);
        //执行创建mapping
        PutMappingResponse putMappingResponse = client.admin().indices().putMapping(putMappingRequest).actionGet();
        logger.info("create mapping(type) result is  [{}] .", putMappingResponse);
        return putMappingResponse;
    }

    /**
     * 创建索引并初始化数据
     *
     * @param indexName 索引名称，相当于数据库名称
     * @param typeName  索引类型，相当于数据库中的表名
     * @param id        id名称，相当于每个表中某一行记录的标识
     * @param jsonData  json数据
     */
    public static IndexResponse createIndex(String indexName, String typeName, String id, String jsonData) {
        //设置索引名称，索引类型，id
        logger.info("开始创建索引并初始化数据");
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexName, typeName, id);
        //创建索引
        //指定存储的数据为json格式
        IndexResponse response = requestBuilder.setSource(jsonData, XContentType.JSON).execute().actionGet();
        logger.info("结束创建索引并初始化数据");
        logger.info("创建索引并初始化数据，响应结果为：{}.", response);
        return response;
    }

    /**
     * 更新索引
     *
     * @param index    索引名称
     * @param type     索引类型
     * @param id       id名称
     * @param jsonData json数据
     */
    public static UpdateResponse updateIndex(String index, String type, Object id,
                                             String jsonData) {
        UpdateRequest updateRequest = new UpdateRequest();
        //设置索引名称
        updateRequest.index(index);
        //设置id
        updateRequest.id(String.valueOf(id));
        //设置索引类型
        updateRequest.type(type);
        //更新数据
        updateRequest.doc(jsonData, XContentType.JSON);
        //执行更新
        UpdateResponse updateResponse = client.update(updateRequest).actionGet();
        logger.info("update index【indexName:={},typeName:={},id:={} 】 is {} . Caption true is success .", index, type, id, updateResponse);
        return updateResponse;
    }

    /**
     * 删除索引
     *
     * @param index 要删除的索引名
     */
    public static DeleteIndexResponse deleteIndex(String index) {
        DeleteIndexResponse deleteIndexResponse =
                client
                        .admin()
                        .indices()
                        .prepareDelete(index)
                        .get();
        logger.info("delete index 【{}】 is {} . Caption: true is success .", deleteIndexResponse.isAcknowledged());
        return deleteIndexResponse;
    }
    /**
     * 在多个types上面执行QueryBuilder搜索
     *
     * @param queryBuilder 查询条件
     * @param index        索引名称
     * @param type         索引类型
     * @return
     */
    public static SearchResponse get(QueryBuilder queryBuilder, String  index, String  type ) {
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type).setQuery(queryBuilder).execute()
                .actionGet();//执行查询
        return searchResponse;
    }
    /**
     * 在多个types上面执行QueryBuilder搜索
     *
     * @param queryBuilder 查询条件
     * @param index        索引名称
     * @param types         索引类型 多个types（mapping）
     * @return
     */
    public static SearchResponse get(QueryBuilder queryBuilder, String  index, String ... types ) {
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(types).setQuery(queryBuilder).execute()
                .actionGet();//执行查询
        return searchResponse;
    }

    /**
     * 在多个index上面执行QueryBuilder搜索
     *
     * @param type         索引类型 多个types（mapping）
     * @param queryBuilder 查询条件
     * @param indics        索引名称
     * @return
     */
    public static SearchResponse get( String  type , QueryBuilder queryBuilder,String ... indics) {
        SearchResponse searchResponse = client.prepareSearch(indics)
                .setTypes(type).setQuery(queryBuilder).execute()
                .actionGet();//执行查询
        return searchResponse;
    }

    /**
     * 全栈查询
     * @param queryBuilder
     * @return
     */
    public static SearchResponse get(QueryBuilder queryBuilder) {
        SearchResponse response = client.prepareSearch().setPostFilter(queryBuilder)
                .execute().actionGet();
        return response;
    }

    /**
     * 高亮显示查询 highlightBuilderQuery
     *
     * @param fieldName  查询检索字段名称
     * @param searchText 检索包含的分词
     * @param tag        高亮标签
     * @param indices    索引名称
     * @return SearchResponse
     */
    public static SearchResponse get(String fieldName, String searchText, String tag, String... indices) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(fieldName, searchText);
        HighlightBuilder hhb = new HighlightBuilder();
        hhb.preTags("<" + tag + ">");
        hhb.postTags("</" + tag + ">");
        hhb.field(fieldName);
        SearchResponse searchResponse = client.prepareSearch(indices).setQuery(queryBuilder).highlighter(hhb).execute().actionGet();
        return searchResponse;
    }

    /**
     * 高亮显示查询 highlightBuilderQuery
     *
     * @param queryBuilder     查询检索条件
     * @param highlightBuilder 高亮样式
     * @param indices          索引名称
     * @return SearchResponse
     */
    public static SearchResponse get(QueryBuilder queryBuilder, HighlightBuilder highlightBuilder, String... indices) {
        SearchResponse searchResponse = client.prepareSearch(indices).setQuery(queryBuilder).highlighter(highlightBuilder).execute().actionGet();
        return searchResponse;
    }

    /**
     * 通过索引，类型，id精确查询
     *
     * @param index
     * @param type
     * @param id
     * @return GetResponse
     */
    public static GetResponse get(String index, String type, Object id) {
        GetResponse response = client.prepareGet(index, type, String.valueOf(id)).execute().actionGet();
        logger.info("Search Data By【index:={},type:={},id:={}】 of prepareGet Method .", index, type, id);
        logger.info("Get GetResponse Data:={}.", response);
        return response;
    }

    /**
     * 根据ID查询一条数据记录。
     *
     * @param id 要查询数据的ID。
     * @return 返回查询出来的记录对象的json字符串。
     */
    public static String get(String index, String type, String id) {
        // 准备进行get操作，此时还有真正地执行get操作。（与直接get的区别）
        GetResponse getResponse = client
                .prepareGet()
                .setIndex(index)
                .setType(type)
                .setId(id)
                .get();
        return getResponse.getSourceAsString();
    }

    /**
     * 获取现有节点
     *
     * @return
     */
    public static List<DiscoveryNode> getNodes() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        logger.info("search nodes by searchNodes method, get nodes :={}.", nodes);
        return nodes;
    }

    /**
     * 关闭elasticsearch
     */
    public static void close() {
        if (client != null) {
            client.close();
            logger.info("Elasticsearch Client Close Success. time:{}", new Date());
        }
    }
}
