package ave.arch.sample.data.network

object ServerUrls {
    open class ServerUrl(val url: String)

    class Github : ServerUrl("https://raw.githubusercontent.com/")
}