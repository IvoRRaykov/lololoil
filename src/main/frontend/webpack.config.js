var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
  devtool: 'source-map',

  entry: {
    proxiadExtranetApp: './src/index.jsx'
  },
  output: {
    path: '../../../src/main/resources/static',
    filename: 'ProxiadExtranetApp.js',
    publicPath: '/'
  },
  module: {
    loaders: [
      {
        test: /\.(jsx|js)?$/,
        include: [ path.resolve(__dirname, './src') ],
        loader: 'babel', query: { presets: ['es2015', 'react'] }
      },
      {
        test: /\.scss$/,
        loaders: ['style-loader', 'css-loader', 'sass-loader']
      },
      {
          test: /\.html$/,
          loaders: ['raw-loader']
      },
      {
          test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|otf|ico)$/,
          loader: 'file?name=assets/[name].[hash].[ext]'
      },
      {
          test: /\.css$/,
          loaders: ['style', 'css']
      },
      {
        test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=application/font-woff'
      }, {
        test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=application/font-woff'
      }, {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=application/octet-stream'
      }, {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'file'
      }, {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=image/svg+xml'
      }
    ]
  },
  plugins: [
    new CopyWebpackPlugin([
        {
            from: path.resolve(__dirname, './assets/images'),
            to: 'assets/images'
        }
    ]),
    new CopyWebpackPlugin([
        {
            from: path.resolve(__dirname, './assets/fonts'),
            to: 'assets/fonts'
        }
    ]),
    new HtmlWebpackPlugin({
        template: 'index.html'
    }),
    new webpack.ProvidePlugin({
        $: "jquery",
        jQuery: "jquery",
        'window.Tether': "tether"
    })
  ]
};
